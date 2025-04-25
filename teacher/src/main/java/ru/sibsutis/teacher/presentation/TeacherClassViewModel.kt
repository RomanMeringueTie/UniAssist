package ru.sibsutis.teacher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.core.presentation.State
import ru.sibsutis.teacher.domain.GetTeacherClassUseCase
import ru.sibsutis.teacher.ui.ClassConverter
import ru.sibsutis.teacher.ui.ClassUI

class TeacherClassViewModel (
    private val classConverter: ClassConverter,
    private val getTeacherClassUseCase: GetTeacherClassUseCase,
    private val id: String
) : ViewModel() {
    private val _state: MutableStateFlow<State<ClassUI>> =
        MutableStateFlow(State.Loading)
    val state: StateFlow<State<ClassUI>> = _state

    init {
        loadClass(id)
    }

    private fun loadClass(id: String) {
        if(_state.value != State.Loading)
            return
        viewModelScope.launch {
            val result = getTeacherClassUseCase(id)
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