package ru.sibsutis.student.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.sibsutis.student.R
import ru.sibsutis.student.data.model.ClassType

@Composable
fun convertType(typeModel: ClassType): String {
    return when (typeModel) {
        ClassType.LECTURE -> stringResource(R.string.lecture)
        ClassType.PRACTICE -> stringResource(R.string.practice)
        ClassType.LABORATORY -> stringResource(R.string.laboratory)
    }
}