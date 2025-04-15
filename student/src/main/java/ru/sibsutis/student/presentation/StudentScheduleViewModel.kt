package ru.sibsutis.student.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import ru.sibsutis.core.presentation.State
import ru.sibsutis.student.domain.GetStudentScheduleUseCase
import ru.sibsutis.student.ui.ClassConverter

class StudentScheduleViewModel(
    private val classConverter: ClassConverter,
    private val getStudentScheduleUseCase: GetStudentScheduleUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(StudentScheduleState())
    val state: StateFlow<StudentScheduleState> = _state

    init {
        loadSchedule()
    }

    fun changePickedDate(date: LocalDate) {
        _state.value = _state.value.copy(
            date = date,
            listState = State.Loading
        )
        loadSchedule()
    }

    private fun loadSchedule() {
        if (_state.value.listState !is State.Loading) {
            return
        }
        viewModelScope.launch {
            val result = getStudentScheduleUseCase(_state.value.date)
            result.fold(
                onSuccess = {
                    _state.value =
                        _state.value.copy(
                            listState = State.Content(
                                content = classConverter.convertList(it)
                            )
                        )
                },
                onFailure = {
                    _state.value =
                        _state.value.copy(
                            listState = State.Failure(
                                message = it.message ?: "Unknown Error"
                            )
                        )
                }
            )
        }
    }
}