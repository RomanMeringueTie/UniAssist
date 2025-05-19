package ru.sibsutis.teacher.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.sibsutis.teacher.R
import ru.sibsutis.teacher.data.model.ClassType

@Composable
internal fun convertType(typeModel: ClassType): String {
    return when (typeModel) {
        ClassType.LECTURE -> stringResource(R.string.lecture)
        ClassType.PRACTICE -> stringResource(R.string.practice)
        ClassType.LABORATORY -> stringResource(R.string.laboratory)
    }
}