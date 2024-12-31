package ru.sibsutis.student.presentation

import androidx.compose.runtime.Immutable
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.measureTime

data class StudentScheduleState(
    val listState: StudentScheduleListState = StudentScheduleListState.Loading,
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)