package ru.sibsutis.core.utils

interface TokenProvider {
    fun get(): String?
}