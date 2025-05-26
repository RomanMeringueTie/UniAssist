package ru.sibsutis.teacher.domain

internal suspend fun <T> repositoryCall(block: suspend () -> T): Result<T> {
    return try {
        val result = block()
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}