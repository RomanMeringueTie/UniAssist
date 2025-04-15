package ru.sibsutis.authorization.data.model

data class User(
    val token: String,
    val fullName: String,
    val role: Role,
    val unit: String,
)