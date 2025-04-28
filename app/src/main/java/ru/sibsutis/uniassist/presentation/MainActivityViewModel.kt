package ru.sibsutis.uniassist.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sibsutis.authorization.data.manager.SecureSharedPrefs
import ru.sibsutis.uniassist.navigation.Route

class MainActivityViewModel(secureSharedPrefs: SecureSharedPrefs) :
    ViewModel() {

    private val _startDestination: MutableStateFlow<Route> =
        MutableStateFlow(Route.AuthorizationRoute)
    val startDestination: StateFlow<Route> = _startDestination

    var login: String? = null
        private set
    var password: String? = null
        private set

    init {
        login = secureSharedPrefs.decryptData("login")
        password = secureSharedPrefs.decryptData("password")
        if (login != null && password != null) {
            _startDestination.value = Route.BackgroundAuthorizationRoute(login!!, password!!)
        }
    }
}