package ru.sibsutis.student.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.Response
import ru.sibsutis.student.data.model.Task

class ClassConverter {

    fun convertList(classModelList: List<ClassModel>): List<ClassUI> {
        return classModelList.map {
            convertItem(it)
        }
    }

    fun convertItem(classModel: ClassModel): ClassUI {
        return ClassUI(
            id = classModel.id,
            subject = classModel.subject,
            startTime = classModel.startTime.toString(),
            endTime = classModel.endTime.toString(),
            type = classModel.type,
            teacher = classModel.teacher,
            classroom = classModel.classroom,
            task = convertTask(classModel.task)
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
                body = it.body,
                mark = it.mark
            )
        }.toImmutableList()
    }


}