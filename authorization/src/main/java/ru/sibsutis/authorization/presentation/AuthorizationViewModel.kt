package ru.sibsutis.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.authorization.data.manager.SecureSharedPrefs
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.authorization.domain.GetTokenUseCase

class AuthorizationViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val secureSharedPrefs: SecureSharedPrefs
) : ViewModel() {

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
        _state.value = _state.value.copy(isLoading = false, error = null)
        isCredentialsValid()
        getToken()
    }

    private fun isCredentialsValid() {
        if (_state.value.login.isBlank() || _state.value.password.isBlank())
            _state.value = _state.value.copy(isLoading = false, error = "Invalid credentials")
    }

    private fun getToken() {
        if (_state.value.isLoading == true)
            return
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = getTokenUseCase(_state.value.login, _state.value.password)
            result.fold(
                onSuccess = {
                    secureSharedPrefs.encryptData(_state.value.login, "login")
                    secureSharedPrefs.encryptData(_state.value.password, "password")
                    _state.value = _state.value.copy(isLoading = false)
                    UserData.apply {
                        token = it.token
                        fullName =
                            it.fullName.let { "${it.lastName} ${it.firstName} ${it.middleName}" }
                        role.value = it.role
                        unit = it.unit
                    }
                },
                onFailure = {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            error = it.message ?: "Unknown Error"
                        )
                }
            )
        }
    }
}