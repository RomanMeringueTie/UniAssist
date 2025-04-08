package ru.sibsutis.teacher.data.service

import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.model.ClassType
import ru.sibsutis.teacher.data.model.Task

class TeacherService(private val ktorClient: KtorClient) {

    suspend fun getSchedule(date: LocalDate) : List<ClassModel> {
        delay(1000)
        return listOf(
            ClassModel(
                id = 0,
                subject = "Схемотехника",
                startTime = LocalTime(8, 0),
                endTime = LocalTime(9, 35),
                type = ClassType.LABORATORY,
                group = "ИВ-222",
                classroom = "1 - 201",
                task = Task(0, "Лабораторная № 3", "Сложная лаба")
            ),
            ClassModel(
                id = 1,
                subject = "Сетевое программирование",
                startTime = LocalTime(9, 50),
                endTime = LocalTime(11, 25),
                type = ClassType.PRACTICE,
                group = "ИВ-221",
                classroom = "4 - 401"
            ),
            ClassModel(
                id = 2,
                subject = "Сети ЭВМ",
                startTime = LocalTime(13, 45),
                endTime = LocalTime(15, 20),
                type = ClassType.LECTURE,
                group = "ИВ, ИС, ИП",
                classroom = "1 - 218"
            ),
            ClassModel(
                id = 3,
                subject = "ПВТ",
                startTime = LocalTime(15, 35),
                endTime = LocalTime(17, 10),
                type = ClassType.PRACTICE,
                group = "ИС-242",
                classroom = "4 - 406",
                task = Task(0, "Лабораторная № 2", "Легкая лаба")
            ),
            ClassModel(
                id = 5,
                subject = "Сети ЭВМ и Телекоммуникации",
                startTime = LocalTime(17, 25),
                endTime = LocalTime(19, 0),
                type = ClassType.PRACTICE,
                group = "ИВ-223",
                classroom = "4 - 402",
            )
        )
    }
}