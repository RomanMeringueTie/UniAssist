package ru.sibsutis.teacher.presentation

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

data class TeacherScheduleState (
    val listState: TeacherScheduleListState = TeacherScheduleListState.Loading,
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)