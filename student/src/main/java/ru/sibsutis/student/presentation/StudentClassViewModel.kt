package ru.sibsutis.student.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.student.domain.GetStudentClassUseCase

class StudentClassViewModel(
    private val getStudentClassUseCase: GetStudentClassUseCase
) :
    ViewModel() {

    private val _state: MutableStateFlow<StudentClassState> =
        MutableStateFlow(StudentClassState.Loading)
    val state: StateFlow<StudentClassState> = _state

    fun loadClass(id: Int) {
        if (_state.value !is StudentClassState.Loading)
            return
        viewModelScope.launch {
            val result = getStudentClassUseCase(id)
            result.fold(
                onSuccess = { _state.value = StudentClassState.Content(classItem = it) },
                onFailure = { _state.value = StudentClassState.Failure(message = it.message) }
            )
        }
    }

}