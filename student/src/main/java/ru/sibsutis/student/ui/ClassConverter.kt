package ru.sibsutis.student.ui

import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.LessonModel

class ClassConverter {

    fun convertList(classModelList: List<LessonModel>): List<ClassUI> {
        return classModelList.map {
            convertItem(
                ClassModel(
                    lesson = it,
                    task = null,
                    response = null
                )
            )
        }
    }

    fun convertItem(classModel: ClassModel): ClassUI {
        return ClassUI(
            id = classModel.lesson.id,
            subject = classModel.lesson.subject,
            startTime = classModel.lesson.startTime.toString(),
            endTime = classModel.lesson.endTime.toString(),
            type = classModel.lesson.type,
            teacher = classModel.lesson.fullName.let { "${it.lastName} ${it.firstName} ${it.middleName}" },
            classroom = classModel.lesson.classroom,
            task = classModel.task,
            response = classModel.response
        )
    }
}