package ru.sibsutis.uniassist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sibsutis.authorization.data.manager.SecureSharedPrefs
import ru.sibsutis.uniassist.navigation.Route

class MainActivityViewModel(application: Application) :
    AndroidViewModel(application = application) {

    private val _startDestination: MutableStateFlow<Route> =
        MutableStateFlow(Route.AuthorizationRoute)
    val startDestination: StateFlow<Route> = _startDestination

    var login: String? = null
        private set
    var password: String? = null
        private set

    init {
        login = SecureSharedPrefs.decryptData("login", application)
        password = SecureSharedPrefs.decryptData("password", application)
        if (login != null && password != null) {
            _startDestination.value = Route.BackgroundAuthorizationRoute(login!!, password!!)
        }
    }
}