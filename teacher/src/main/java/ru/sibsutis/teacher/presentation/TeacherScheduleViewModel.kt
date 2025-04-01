package ru.sibsutis.teacher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import ru.sibsutis.core.presentation.State
import ru.sibsutis.teacher.domain.GetTeacherScheduleUseCase
import ru.sibsutis.teacher.ui.ClassConverter

class TeacherScheduleViewModel(
    private val getTeacherScheduleUseCase: GetTeacherScheduleUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TeacherScheduleState())
    val state: StateFlow<TeacherScheduleState> = _state

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
            val result = getTeacherScheduleUseCase(_state.value.date)
            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(
                        listState = State.Content(
                            content = ClassConverter().convertList(it)
                        )
                    )
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        listState = State.Failure(
                            message = it.message ?: "Unknown Error"
                        )
                    )
                }
            )
        }
    }
}