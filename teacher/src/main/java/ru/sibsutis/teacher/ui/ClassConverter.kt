package ru.sibsutis.teacher.ui

import ru.sibsutis.teacher.data.model.ClassModel

class ClassConverter {

    fun convertList(classModelList: List<ClassModel>) : List<ClassUI> {
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
            group = classModel.group,
            classroom = classModel.classroom,
            taskHeader = classModel.task?.header
        )
    }
}