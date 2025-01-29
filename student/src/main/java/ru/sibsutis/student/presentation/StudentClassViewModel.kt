package ru.sibsutis.student.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.student.domain.GetStudentClassUseCase
import ru.sibsutis.student.domain.GetStudentClassUseCaseImpl
import ru.sibsutis.student.ui.ClassConverter

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
                onSuccess = {
                    _state.value =
                        StudentClassState.Content(classItem = ClassConverter().convertItem(it))
                },
                onFailure = { _state.value = StudentClassState.Failure(message = it.message) }
            )
        }
    }

}