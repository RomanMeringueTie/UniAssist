package ru.sibsutis.authorization.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.authorization.data.manager.SecureSharedPrefs
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.authorization.domain.GetTokenUseCase
import ru.sibsutis.core.presentation.State

class BackgroundAuthorizationViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow<State<Any>>(value = State.Loading)
    val state: StateFlow<State<Any>> = _state

    init {
        viewModelScope.launch {
            val login = SecureSharedPrefs.decryptData("login", context)!!
            val password = SecureSharedPrefs.decryptData("password", context)!!
            val result = getTokenUseCase(login, password)
            result.fold(
                onSuccess = {
                    _state.value = State.Content(true)
                    UserData.apply {
                        token = it.token
                        fullName = it.fullName
                        role.value = it.role
                        unit = it.unit
                    }
                },
                onFailure = {
                    _state.value = State.Failure(message = it.message ?: "Unknown Error")
                }
            )
        }
    }
}