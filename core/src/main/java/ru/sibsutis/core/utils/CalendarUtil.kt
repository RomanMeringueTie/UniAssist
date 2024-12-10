package ru.sibsutis.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import ru.sibsutis.core.R
import java.util.Calendar

class CalendarUtil {
    private val calendar = Calendar.getInstance()
    val weekDaysIds = listOf(
        R.string.monday,
        R.string.tuesday,
        R.string.wednesday,
        R.string.thursday,
        R.string.friday,
        R.string.saturday
    )

    private val monthsIds = listOf(
        R.string.january,
        R.string.february,
        R.string.march,
        R.string.april,
        R.string.may,
        R.string.june,
        R.string.july,
        R.string.august,
        R.string.september,
        R.string.october,
        R.string.november,
        R.string.december
    )

    @Composable
    fun getToday(): String {
        val calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        return "$day ${stringResource(monthsIds[month])} $year"
    }

    fun getFirstWeekDay(): Int {
        return calendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_WEEK) + 1
    }

    fun getTodayInt(): Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }
}