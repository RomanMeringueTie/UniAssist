package ru.sibsutis.student.data.service

import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.model.Class
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import ru.sibsutis.student.data.model.ClassType
import ru.sibsutis.student.data.model.Task
import java.time.LocalDate

@Serializable
data class Classes(val classes: List<Class>)

class StudentService(private val ktorClient: KtorClient) {
    //    suspend fun getSchedule(date: Long): List<Class> {
//        val response: Classes = ktorClient.client.get("/student_schedule/$date") {
//            headers {
//                append(HttpHeaders.AuthenticationInfo, "id")
//            }
//        }.body()
//        return response.classes
//    }
    suspend fun getSchedule(date: LocalDate): List<Class> {
        delay(10)
        return listOf(
            Class(
                id = date,
                subject = "ПВТ",
                startTime = 3,
                endTime = 10,
                type = ClassType.LABORATORY,
                teacher = "Tanya",
                classroom = "1 - 400л",
                task = Task("Лабораторная 4")
            )
        )
    }
}