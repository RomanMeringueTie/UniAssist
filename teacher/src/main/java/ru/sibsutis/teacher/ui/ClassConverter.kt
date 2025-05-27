package ru.sibsutis.teacher.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.teacher.data.model.LessonModel
import ru.sibsutis.teacher.data.model.Response
import ru.sibsutis.teacher.data.model.Task

class ClassConverter {

    fun convertList(lessonModelList: List<LessonModel>) : List<ClassUI> {
        return lessonModelList.map {
            convertItem(it, null)
        }
    }

    fun convertItem(lesson: LessonModel, task: Task?): ClassUI {
        return ClassUI(
            id = lesson.id,
            subject = lesson.subject,
            startTime = lesson.startTime.toString(),
            endTime = lesson.endTime.toString(),
            type = lesson.type,
            group = lesson.group,
            classroom = lesson.classroom,
            task = convertTask(task)
        )
    }

    private fun convertTask(taskModel: Task?): TaskUI? {
        return if (taskModel != null) {
            TaskUI(
                header = taskModel.header,
                body = taskModel.body,
                responses = convertTaskResponses(taskModel.responses)
            )
        } else null
    }

    private fun convertTaskResponses(responseModelsList: List<Response>): ImmutableList<ResponseUI> {
        return responseModelsList.map {
            ResponseUI(
                id = it.id,
                body = it.body,
                mark = it.mark,
                fullName = it.fullName.let { "${it.lastName} ${it.firstName} ${it.middleName}" }
            )
        }.toImmutableList()
    }
}