package ru.sibsutis.student.ui

import android.annotation.SuppressLint
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toJavaInstant
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getWeekDay(localDate: LocalDate): String {
    val simpleDateFormat = SimpleDateFormat("EE")
    val date = Date.from(
        localDate.atStartOfDayIn(TimeZone.currentSystemDefault()).toJavaInstant()
    )
    val dayOfWeek = simpleDateFormat.format(date).uppercase()
    return dayOfWeek
}