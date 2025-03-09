package ru.sibsutis.authorization.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.authorization.data.manager.SecureSharedPrefs
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.authorization.domain.GetTokenUseCase

class AuthorizationViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val application: Application
) : AndroidViewModel(application = application) {

    private val _state = MutableStateFlow(AuthorizationScreenState())
    val state = _state

    fun setLogin(login: String) {
        _state.value = _state.value.copy(login = login)
    }

    fun setPassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun changePasswordVisibility() {
        _state.value = _state.value.copy(isPasswordShown = _state.value.isPasswordShown.not())
    }

    fun authorize() {
        _state.value = _state.value.copy(state = LoginState.Initial)
        isCredentialsValid()
        getToken()
    }

    private fun isCredentialsValid() {
        if (_state.value.login.isBlank() || _state.value.password.isBlank())
            _state.value = _state.value.copy(state = LoginState.Failure("Invalid credentials"))
    }

    private fun getToken() {
        if (_state.value.state is LoginState.Failure || _state.value.state is LoginState.Content)
            return
        _state.value = _state.value.copy(state = LoginState.Loading)
        viewModelScope.launch {
            val result =
                getTokenUseCase(_state.value.login, _state.value.password)
            result.fold(
                onSuccess = {
                    SecureSharedPrefs.encryptData(_state.value.login, "login", application)
                    SecureSharedPrefs.encryptData(_state.value.password, "password", application)
                    _state.value = _state.value.copy(state = LoginState.Content(it))
                    UserData.apply {
                        token = it.token
                        fullName = it.fullName
                        role = it.role
                        unit = it.unit
                    }
                },
                onFailure = {
                    _state.value =
                        _state.value.copy(state = LoginState.Failure(it.message ?: "Unknown Error"))
                }
            )
        }
    }
}