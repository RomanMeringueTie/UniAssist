package ru.sibsutis.student.presentation

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class StudentScheduleState(
    val listState: StudentScheduleListState = StudentScheduleListState.Loading,
    val date: LocalDate = LocalDate.now()
)