package ru.sibsutis.student.data.service

import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.model.Class
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ru.sibsutis.student.data.model.ClassType
import ru.sibsutis.student.data.model.Task

//@Serializable
//data class Classes(val classes: List<Class>)

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
        delay(1000)
        return listOf(
            Class(
                id = 0,
                subject = "Схемотехника",
                startTime = LocalTime(8, 0),
                endTime = LocalTime(9, 35),
                type = ClassType.LABORATORY,
                teacher = "Иванов В. П.",
                classroom = "1 - 201",
                task = Task("Лабораторная № 3")
            ),
            Class(
                id = 1,
                subject = "Архитектура ЭВМ",
                startTime = LocalTime(9, 50),
                endTime = LocalTime(11, 25),
                type = ClassType.LABORATORY,
                teacher = "Сидоров С. А.",
                classroom = "5 - 213",
                task = Task("Лабораторная № 4")
            ),
            Class(
                id = 2,
                subject = "Экономика",
                startTime = LocalTime(11, 40),
                endTime = LocalTime(13, 15),
                type = ClassType.LABORATORY,
                teacher = "Калинина М. Л.",
                classroom = "3 - 416",
            ),
            Class(
                id = 3,
                subject = "Философия",
                startTime = LocalTime(13, 45),
                endTime = LocalTime(15, 20),
                type = ClassType.LABORATORY,
                teacher = "Калинина М. Л.",
                classroom = "3 - 416",
            ),
            Class(
                id = 4,
                subject = "Русский язык",
                startTime = LocalTime(15, 35),
                endTime = LocalTime(17, 10),
                type = ClassType.LABORATORY,
                teacher = "Калинина М. Л.",
                classroom = "3 - 416",
            ),
            Class(
                id = 5,
                subject = "Сети ЭВМ и Телекоммуникации",
                startTime = LocalTime(17, 25),
                endTime = LocalTime(19, 0),
                type = ClassType.LABORATORY,
                teacher = "Калинина М. Л.",
                classroom = "3 - 416",
            )
        )
    }
}