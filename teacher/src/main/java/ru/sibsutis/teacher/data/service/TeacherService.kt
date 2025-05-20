package ru.sibsutis.teacher.data.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ru.sibsutis.core.network.KtorClient
import ru.sibsutis.teacher.data.model.ClassModel
import ru.sibsutis.teacher.data.model.ClassType
import ru.sibsutis.teacher.data.model.Response
import ru.sibsutis.teacher.data.model.Task

class TeacherService(private val ktorClient: KtorClient) {

    suspend fun getSchedule(date: LocalDate): List<ClassModel> {
        return ktorClient.client.get("schedule/teacher?date=2024-12-10") {
        }.body()
    }

    companion object {
        val responsesModel = mutableListOf<Response>(
            Response(
                id = "0",
                studentId = "0",
                body = "Выполнил четвертую лабораторную",
                mark = null
            ),
            Response(
                id = "1",
                studentId = "1",
                body = "Сделал вторую лабораторную",
                mark = null
            )
        )
        var taskModel: Task? = null
        var classModel = ClassModel(
            id = "0",
            subject = "Сетевое программирование",
            startTime = LocalTime(11, 40),
            endTime = LocalTime(13, 15),
            type = ClassType.PRACTICE,
            group = "ИВ-221",
            classroom = "4 - 401",
            task = taskModel
        )
    }

    suspend fun getClass(id: String): ClassModel {
        delay(1000)
        val result = classModel.copy(id = id)
        return result
    }

    suspend fun sendTask(id: String, header: String, body: String) {
        delay(1000)
        taskModel = Task(
            id = id,
            header = header,
            body = body,
            responses = responsesModel
        )
        classModel = classModel.copy(
            task = taskModel
        )
    }

    suspend fun sendMark(id: String, mark: Int) {
        delay(1000)
        val currentTask = taskModel ?: return
        taskModel = currentTask.copy(
            responses = currentTask.responses.map { response ->
                if (response.id == id) {
                    response.copy(mark = mark)
                }
                else
                    response
            }
        )
        classModel = classModel.copy(
            task = taskModel
        )
    }
}