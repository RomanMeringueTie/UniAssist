package ru.sibsutis.student.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.core.presentation.State
import ru.sibsutis.student.domain.GetStudentClassUseCase
import ru.sibsutis.student.ui.ClassConverter
import ru.sibsutis.student.ui.ClassUI

class StudentClassViewModel(
    private val classConverter: ClassConverter,
    private val getStudentClassUseCase: GetStudentClassUseCase,
    private val id: String
) :
    ViewModel() {

    private val _state: MutableStateFlow<State<ClassUI>> =
        MutableStateFlow(State.Loading)
    val state: StateFlow<State<ClassUI>> = _state


    init {
        loadClass(id)
    }


    private fun loadClass(id: String) {
        if (_state.value != State.Loading)
            return
        viewModelScope.launch {
            val result = getStudentClassUseCase(id)
            result.fold(
                onSuccess = {
                    _state.value =
                        State.Content(content = classConverter.convertItem(it))
                },
                onFailure = {
                    _state.value =
                        State.Failure(message = it.message ?: "Unknown Error")
                }
            )
        }
    }

}