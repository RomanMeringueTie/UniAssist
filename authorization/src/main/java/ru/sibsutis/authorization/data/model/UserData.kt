package ru.sibsutis.authorization.data.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object UserData {
    var token: String? = null
    var fullName: String? = null
    var role: MutableState<Role?> = mutableStateOf(null)
    var unit: String? = null
}