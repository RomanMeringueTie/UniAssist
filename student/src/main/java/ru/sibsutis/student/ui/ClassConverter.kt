package ru.sibsutis.student.ui

import ru.sibsutis.student.data.model.ClassModel

class ClassConverter {

    fun convertList(classModelList: List<ClassModel>): List<ClassUI> {
        return classModelList.map {
            convertItem(it)
        }
    }

    private fun convertItem(classModel: ClassModel): ClassUI {
        return ClassUI(
            id = classModel.id,
            subject = classModel.subject,
            startTime = classModel.startTime.toString(),
            endTime = classModel.endTime.toString(),
            type = classModel.type,
            teacher = classModel.teacher,
            classroom = classModel.classroom,
            taskHeader = classModel.task?.header
        )
    }


}