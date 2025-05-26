package ru.sibsutis.teacher.data.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.model.LessonModel

@Serializable
data class SendTask(
    @SerialName("lessonId")
    val id: String,
    @SerialName("header")
    val header: String,
    @SerialName("body")
    val body: String,
    @SerialName("dueTime")
    val dueTime: LocalDateTime,
)

@Serializable
data class SendMark(
    @SerialName("solutionId")
    val id: String,
    @SerialName("mark")
    val mark: Int
)

class TeacherService(private val ktorClient: KtorClient) {

    suspend fun getSchedule(date: LocalDate): List<LessonModel> {
        return ktorClient.client.get("schedule/teacher?date=$date") {
        }.body()
    }

    suspend fun getClass(id: String): ClassModel {
        return ktorClient.client.get("lesson/teacher/$id").body()
    }

    suspend fun sendTask(id: String, title: String, content: String) {
        ktorClient.client.post("task/teacher") {
            setBody(
                SendTask(
                    id = id,
                    header = title,
                    body = content,
                    dueTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                )
            )
        }
    }

    suspend fun sendMark(id: String, mark: Int) {
        //TODO логика обработки nullable Task
        ktorClient.client.patch("solution/teacher") {
            setBody(SendMark(id = id, mark = mark))
        }
    }
}