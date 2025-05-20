package ru.sibsutis.teacher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.core.presentation.State
import ru.sibsutis.teacher.domain.GetTeacherClassUseCase
import ru.sibsutis.teacher.domain.SendTeacherMarkUseCase
import ru.sibsutis.teacher.domain.SendTeacherTaskUseCase
import ru.sibsutis.teacher.ui.ClassConverter

class TeacherClassViewModel (
    private val classConverter: ClassConverter,
    private val getTeacherClassUseCase: GetTeacherClassUseCase,
    private val sendTeacherTaskUseCase: SendTeacherTaskUseCase,
    private val sendTeacherMarkUseCase: SendTeacherMarkUseCase,
    private val id: String
) : ViewModel() {
    private val _state = MutableStateFlow(TeacherClassState())
    val state: StateFlow<TeacherClassState> = _state

    init {
        loadClass(id)
    }

    private fun loadClass(id: String) {
        if(_state.value.classState != State.Loading)
            return
        viewModelScope.launch {
            val result = getTeacherClassUseCase(id)
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

    fun changeTaskValueHeader(newValue: String) {
        _state.value = _state.value.copy(taskValueHeader = newValue)
    }

    fun changeTaskValueBody(newValue: String) {
        _state.value = _state.value.copy(taskValueBody = newValue)
    }

    fun changeTaskDialogStatus() {
        _state.value = _state.value.copy(isDialogShown = _state.value.isDialogShown.not())
    }

    fun changeResponseDialogStatus() {
        _state.value = _state.value.copy(isResponseShown = _state.value.isResponseShown.not())
    }

    fun onSendMark(Id: String, newValue: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(responseState = ResponseState.Loading)
            val result = sendTeacherMarkUseCase(Id, newValue)
            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(responseState = ResponseState.Content)
                    val result = getTeacherClassUseCase(id)
                    result.fold(
                        onSuccess = {
                            _state.value = _state.value.copy(
                                classState = State.Content(content = classConverter.convertItem(it))
                            )
                        },
                        onFailure = {
                            _state.value = _state.value.copy(
                                classState = State.Failure(message = it.message ?: "Unknown Error")
                            )
                        }
                    )
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        responseState = ResponseState.Failure(it.message ?: "Unknown Error")
                    )
                }
            )
        }
    }

    fun onSendTask() {
        viewModelScope.launch {
            _state.value = _state.value.copy(taskState = TaskState.Loading)
            val result = sendTeacherTaskUseCase(id, _state.value.taskValueHeader, _state.value.taskValueBody)
            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(taskState = TaskState.Content)
                    val result = getTeacherClassUseCase(id)
                    result.fold(
                        onSuccess = {
                            _state.value = _state.value.copy(
                                classState = State.Content(content = classConverter.convertItem(it))
                            )
                        },
                        onFailure = {
                            _state.value = _state.value.copy(
                                classState = State.Failure(message = it.message ?: "Unknown Error")
                            )
                        }
                    )
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        taskState = TaskState.Failure(it.message ?: "Unknown Error")
                    )
                }
            )
        }
    }

    fun resetTask() {
        _state.value = _state.value.copy(taskState = TaskState.Initial, taskValueHeader = "", taskValueBody = "")
    }
}