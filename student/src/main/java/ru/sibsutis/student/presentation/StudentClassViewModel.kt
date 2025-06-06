package ru.sibsutis.student.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sibsutis.core.presentation.State
import ru.sibsutis.student.domain.GetStudentClassUseCase
import ru.sibsutis.student.domain.SendStudentResponseUseCase
import ru.sibsutis.student.ui.ClassConverter

class StudentClassViewModel(
    private val classConverter: ClassConverter,
    private val getStudentClassUseCase: GetStudentClassUseCase,
    private val sendStudentResponseUseCase: SendStudentResponseUseCase,
    private val id: String
) :
    ViewModel() {

    private val _state = MutableStateFlow(StudentClassState())
    val state: StateFlow<StudentClassState> = _state


    init {
        loadClass(id)
    }


    private fun loadClass(id: String) {
        if (_state.value.classState != State.Loading) {
            return
        }
        viewModelScope.launch {
            val result = getStudentClassUseCase(id)
            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(
                        classState =
                            State.Content(content = classConverter.convertItem(it))
                    )
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        classState =
                            State.Failure(message = it.message ?: "Unknown Error")
                    )
                }
            )
        }
    }

    fun changeResponseValue(newValue: String) {
        _state.value = _state.value.copy(responseValue = newValue)
    }

    fun changeResponseDialogStatus() {
        _state.value = _state.value.copy(isDialogShown = _state.value.isDialogShown.not())
    }

    fun onSendResponse(taskId: String) {
        _state.value = _state.value.copy(responseState = ResponseState.Loading)

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                sendStudentResponseUseCase(
                    taskId,
                    _state.value.responseValue,
                    id
                )
            }
            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(
                        classState =
                            State.Content(content = classConverter.convertItem(it)),
                        responseState = ResponseState.Content
                    )
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        responseState = ResponseState.Failure(
                            it.message ?: "Unknown Error"
                        )
                    )
                })
        }

    }

    fun resetResponse() {
        _state.value = _state.value.copy(responseState = ResponseState.Initial, responseValue = "")
    }

}