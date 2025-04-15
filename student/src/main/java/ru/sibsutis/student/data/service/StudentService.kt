package ru.sibsutis.student.data.service

import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.ClassType
import ru.sibsutis.student.data.model.Task

class StudentService(private val ktorClient: KtorClient) {

    suspend fun getSchedule(date: LocalDate): List<ClassModel> {
        return ktorClient.client.get("schedule/student?date=2024-12-10") {
            bearerAuth(UserData.token ?: "")
        }.body()
    }

    suspend fun getClass(id: String): ClassModel {
        delay(1000)
        return ClassModel(
            id = id,
            subject = "Схемотехника",
            startTime = LocalTime(8, 0),
            endTime = LocalTime(9, 35),
            type = ClassType.LABORATORY,
            teacher = "Иванов В. П.",
            classroom = "1 - 201",
            task = Task(0, "Лабораторная № 3", "Сложная лаба")
        )
    }
}