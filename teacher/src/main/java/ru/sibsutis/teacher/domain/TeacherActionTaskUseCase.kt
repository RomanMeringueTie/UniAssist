package ru.sibsutis.teacher.domain

interface TeacherActionTaskUseCase {
    suspend fun <T> executeAction(
        action: TeacherAction,
        data: T
    ): Result<Unit>
}

sealed class TeacherAction {
    data class SendTask(val id: String, val title: String, val content: String)
        : TeacherAction ()
    data class SendMark(val id: String, val mark: Int) : TeacherAction()
}