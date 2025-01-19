package ru.sibsutis.core.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.text.SimpleDateFormat
import java.util.Calendar

object CalendarUtil {
    @SuppressLint("SimpleDateFormat")
    @Composable
    fun getToday(): String {
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")
        val date = simpleDateFormat.format(Calendar.getInstance().time)
        return date
    }

    fun getWeekDays(): List<LocalDate> {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        // Первый день недели = сегодняшняя дата - сегодняшний день недели
        val first = today.minus(today.dayOfWeek.value - 1, DateTimeUnit.DAY)
        val weekDaysList = mutableListOf(first)
        for (i in 1 until 6) {
            weekDaysList.add(first.plus(i, DateTimeUnit.DAY))
        }
        return weekDaysList
    }

    fun isToday(date: LocalDate) = Clock.System.todayIn(TimeZone.currentSystemDefault()) == date
}