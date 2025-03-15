package ru.sibsutis.uniassist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sibsutis.authorization.data.manager.SecureSharedPrefs

class MainActivityViewModel(application: Application) :
    AndroidViewModel(application = application) {

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized: StateFlow<Boolean> = _isAuthorized
    var login: String? = null
        private set
    var password: String? = null
        private set

    init {
        login = SecureSharedPrefs.decryptData("login", application)
        password = SecureSharedPrefs.decryptData("password", application)
        if (login != null && password != null) {
            _isAuthorized.value = true
        }
    }
}