package ru.sibsutis.student.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.student.domain.GetStudentScheduleUseCase
import java.time.LocalDate

class StudentScheduleViewModel(
    private val getStudentScheduleUseCase: GetStudentScheduleUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(StudentScheduleState())
    val state: StateFlow<StudentScheduleState> = _state

    init {
        loadSchedule()
    }

    fun changePickedDate(index: Int) {
        _state.value.date = LocalDate.now().plusDays(index.toLong())
        loadSchedule()
    }

    private fun loadSchedule() {
        if (_state.value.listState !is StudentScheduleListState.Loading)
            return
        viewModelScope.launch {
            val result = getStudentScheduleUseCase(_state.value.date)
            if (result.isSuccess)
                _state.value.listState = StudentScheduleListState.Content(result.getOrNull()!!)
            else
                _state.value.listState =
                    StudentScheduleListState.Failure(result.exceptionOrNull()?.message)
        }
    }
}