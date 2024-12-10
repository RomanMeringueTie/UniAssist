package ru.sibsutis.student.presentation

import java.time.LocalDate

data class StudentScheduleState(
    var listState: StudentScheduleListState = StudentScheduleListState.Loading,
    var date: LocalDate = LocalDate.now()
)