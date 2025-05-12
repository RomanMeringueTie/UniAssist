package ru.sibsutis.student.data.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.ClassType
import ru.sibsutis.student.data.model.Response
import ru.sibsutis.student.data.model.Task

class StudentService(private val ktorClient: KtorClient) {

    suspend fun getSchedule(date: LocalDate): List<ClassModel> {
        return ktorClient.client.get("schedule/student?date=2024-12-10") {
        }.body()
    }


    companion object {
        val responsesModel = mutableListOf<Response>()
        var taskModel = Task(
            id = 0,
            header = "Лабораторная № 3",
            body = "Сложная лаба",
            responses = responsesModel
        )
        var classModel = ClassModel(
            id = "0",
            subject = "Схемотехника",
            startTime = LocalTime(8, 0),
            endTime = LocalTime(9, 35),
            type = ClassType.LABORATORY,
            teacher = "Иванов В. П.",
            classroom = "1 - 201",
            task = taskModel
        )
    }

    suspend fun getClass(id: String): ClassModel {
        delay(1000)
        val result = classModel.copy(id = id)
        return result
    }

    suspend fun sendResponse(id: String, body: String) {
        delay(1000)
        responsesModel.add(
            Response(
                id = id,
                studentId = 0,
                body = body,
                mark = null
            )
        )
        taskModel = taskModel.copy(
            responses = responsesModel
        )
        classModel = classModel.copy(
            task = taskModel
        )
    }
}