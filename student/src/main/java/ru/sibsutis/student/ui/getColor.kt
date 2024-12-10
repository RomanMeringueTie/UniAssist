package ru.sibsutis.student.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import ru.sibsutis.core.utils.CalendarUtil
import ru.sibsutis.student.R


@Composable
fun getColor(index: Int, pickedDate: Int): Color {
    return if (pickedDate == index + CalendarUtil().getFirstWeekDay()) colorResource(
        id = R.color.purple_500
    )
    else colorResource(id = R.color.background)
}