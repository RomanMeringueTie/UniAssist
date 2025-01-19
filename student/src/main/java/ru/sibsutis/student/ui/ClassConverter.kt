package ru.sibsutis.student.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.sibsutis.student.R
import ru.sibsutis.student.data.model.ClassModel
import ru.sibsutis.student.data.model.ClassType

class ClassConverter(private val classModel: ClassModel) {
    @Composable
    fun convert(): ClassUI {
        return ClassUI(
            id = classModel.id,
            subject = classModel.subject,
            startTime = classModel.startTime.toString(),
            endTime = classModel.endTime.toString(),
            type = convertType(classModel.type),
            teacher = classModel.teacher,
            classroom = classModel.classroom,
            taskHeader = classModel.task?.header
        )
    }

    @Composable
    private fun convertType(typeModel: ClassType): String {
        return when (typeModel) {
            ClassType.LECTURE -> stringResource(R.string.lecture)
            ClassType.PRACTICE -> stringResource(R.string.practice)
            ClassType.LABORATORY -> stringResource(R.string.laboratory)
        }
    }
}