package ru.sibsutis.core.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import java.text.SimpleDateFormat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.util.Calendar
import java.util.Locale

object CalendarUtil {
    @SuppressLint("SimpleDateFormat")
    @Composable
    fun getToday(): String {
        val date = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
        val list = date.format(Calendar.getInstance(Locale("ru")).time).split(' ')
        return "${list[0]} ${list[1].replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }} ${list[2]}"
    }

    fun getWeekDays(): List<LocalDate> {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val first = today.minus(today.dayOfWeek.value - 1, DateTimeUnit.DAY)
        val weekDaysList = mutableListOf(first)
        for (i in 1 until 6) {
            weekDaysList.add(first.plus(i, DateTimeUnit.DAY))
        }
        return weekDaysList
    }

    fun isToday(date: LocalDate) = Clock.System.todayIn(TimeZone.currentSystemDefault()) == date
}