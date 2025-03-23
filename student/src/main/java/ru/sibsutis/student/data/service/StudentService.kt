package ru.sibsutis.student.data.service

import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.ClassType
import ru.sibsutis.student.data.model.Task

class StudentService(private val ktorClient: KtorClient) {
    //    suspend fun getSchedule(date: Long): List<Class> {
//        val response: Classes = ktorClient.client.get("/student_schedule/$date") {
//            headers {
//                append(HttpHeaders.AuthenticationInfo, "id")
//            }
//        }.body()
//        return response.classes
//    }
    private val list = listOf(
        ClassModel(
            id = 0,
            subject = "Схемотехника",
            startTime = LocalTime(8, 0),
            endTime = LocalTime(9, 35),
            type = ClassType.LABORATORY,
            teacher = "Иванов В. П.",
            classroom = "1 - 201",
            task = Task(0, "Лабораторная № 3", "Сложная лаба")
        ),
        ClassModel(
            id = 1,
            subject = "Архитектура ЭВМ",
            startTime = LocalTime(9, 50),
            endTime = LocalTime(11, 25),
            type = ClassType.LABORATORY,
            teacher = "Сидоров С. А.",
            classroom = "5 - 213",
            task = Task(0, "Лабораторная № 4", "Ещё более сложная лаба")
        ),
        ClassModel(
            id = 2,
            subject = "Экономика",
            startTime = LocalTime(11, 40),
            endTime = LocalTime(13, 15),
            type = ClassType.LABORATORY,
            teacher = "Калинина М. Л.",
            classroom = "3 - 416",
        ),
        ClassModel(
            id = 3,
            subject = "Философия",
            startTime = LocalTime(13, 45),
            endTime = LocalTime(15, 20),
            type = ClassType.LABORATORY,
            teacher = "Калинина М. Л.",
            classroom = "3 - 416",
        ),
        ClassModel(
            id = 4,
            subject = "Русский язык",
            startTime = LocalTime(15, 35),
            endTime = LocalTime(17, 10),
            type = ClassType.LABORATORY,
            teacher = "Калинина М. Л.",
            classroom = "3 - 416",
        ),
        ClassModel(
            id = 5,
            subject = "Сети ЭВМ и Телекоммуникации",
            startTime = LocalTime(17, 25),
            endTime = LocalTime(19, 0),
            type = ClassType.LABORATORY,
            teacher = "Калинина М. Л.",
            classroom = "3 - 416",
        )
    )

    suspend fun getSchedule(date: LocalDate): List<ClassModel> {
        delay(1000)
        return list

//        return ktorClient.client.get("student_schedule/${date.toEpochDays()}").body()
    }

    suspend fun getClass(id: Int): ClassModel {
        delay(1000)
        return list[id]
    }
}