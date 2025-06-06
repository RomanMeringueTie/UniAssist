package ru.sibsutis.teacher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.core.presentation.State
import ru.sibsutis.teacher.data.model.ClassModel
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
                onSuccess = { classModel: ClassModel ->
                    _state.value = _state.value.copy(
                        classState =
                            State.Content(content = classConverter.convertItem(classModel.lesson, classModel.task))
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

    fun changeTaskValueTitle(newValue: String) {
        _state.value = _state.value.copy(taskValueTitle = newValue)
    }

    fun changeTaskValueContent(newValue: String) {
        _state.value = _state.value.copy(taskValueContent = newValue)
    }

    fun changeTaskDialogStatus() {
        _state.value = _state.value.copy(isDialogShown = _state.value.isDialogShown.not())
    }

    fun changeResponseDialogStatus() {
        _state.value = _state.value.copy(isResponseShown = _state.value.isResponseShown.not())
    }

    fun onSendMark(studentId: String, newValue: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(responseStates = (_state.value.responseStates + (studentId to ResponseState.Loading)).toImmutableMap())
            val result = sendTeacherMarkUseCase(id, studentId, newValue)
            result.fold(
                onSuccess = { classModel: ClassModel ->
                    _state.value = _state.value.copy(
                        responseStates = (_state.value.responseStates + (studentId to ResponseState.Content)).toImmutableMap(),
                        classState = State.Content(content = classConverter.convertItem(classModel.lesson, classModel.task))
                    )
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        responseStates = (_state.value.responseStates + (studentId to ResponseState.Failure(it.message ?: "Unknown Error"))).toImmutableMap()
                    )
                }
            )
        }
    }

    fun onSendTask() {
        viewModelScope.launch {
            _state.value = _state.value.copy(taskState = TaskState.Loading)
            val result = sendTeacherTaskUseCase(id, _state.value.taskValueTitle, _state.value.taskValueContent)
            result.fold(
                onSuccess = { classModel: ClassModel ->
                    _state.value = _state.value.copy(
                        taskState = TaskState.Content,
                        classState = State.Content(content = classConverter.convertItem(classModel.lesson, classModel.task))
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
        _state.value = _state.value.copy(taskState = TaskState.Initial, taskValueTitle = "", taskValueContent = "")
    }
}