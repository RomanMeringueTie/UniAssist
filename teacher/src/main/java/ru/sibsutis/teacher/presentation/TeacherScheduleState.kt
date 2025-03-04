package ru.sibsutis.teacher.presentation

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import ru.sibsutis.core.presentation.State
import ru.sibsutis.teacher.ui.ClassUI

data class TeacherScheduleState (
    val listState: State<List<ClassUI>> = State.Loading,
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)