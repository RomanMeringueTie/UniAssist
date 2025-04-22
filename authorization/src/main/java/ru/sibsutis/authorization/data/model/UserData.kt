package ru.sibsutis.authorization.data.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.sibsutis.core.utils.TokenProvider

object UserData : TokenProvider {
    var token: String? = null
    var fullName: String? = null
    var role: MutableState<Role?> = mutableStateOf(null)
    var unit: String? = null
    override fun get(): String? = token
}