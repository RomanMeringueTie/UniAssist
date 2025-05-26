package ru.sibsutis.student.data.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.LessonModel

@Serializable
data class SendResponse(
    @SerialName("taskId")
    val taskId: String,
    @SerialName("body")
    val body: String,
    @SerialName("type")
    val type: String
)

class StudentService(private val ktorClient: KtorClient) {

    suspend fun getSchedule(date: LocalDate): List<LessonModel> {
        return ktorClient.client.get("schedule/student?date=$date") {
        }.body()
    }

    suspend fun getClass(id: String): ClassModel {
        return ktorClient.client.get("lesson/student/$id").body()
    }

    suspend fun sendResponse(id: String, body: String) {
        ktorClient.client.post("solution/student") {
            setBody(SendResponse(taskId = id, body = body, type = "TEXT"))
        }
    }
}