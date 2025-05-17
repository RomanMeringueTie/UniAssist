package ru.sibsutis.teacher.data.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.model.ClassType
import ru.sibsutis.teacher.data.model.Task

class TeacherService(private val ktorClient: KtorClient) {

    suspend fun getSchedule(date: LocalDate): List<ClassModel> {
        return ktorClient.client.get("schedule/teacher?date=2024-12-10") {
        }.body()
    }

    suspend fun getClass(id: String): ClassModel {
        delay(1000)
        return ClassModel(
            id = id,
            subject = "Сетевое программирование",
            startTime = LocalTime(11, 40),
            endTime = LocalTime(13, 15),
            type = ClassType.PRACTICE,
            group = "ИВ-221",
            classroom = "4 - 401",
            task = Task(0, "Лабораторная № 2", "Создание ftp клиента")
        )
    }
}