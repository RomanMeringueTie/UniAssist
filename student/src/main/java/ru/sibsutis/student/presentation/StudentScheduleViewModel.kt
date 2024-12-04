package ru.sibsutis.student.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.student.R
import ru.sibsutis.student.data.model.Class
import ru.sibsutis.student.domain.GetStudentScheduleUseCase
import java.util.Calendar

sealed interface StudentScheduleState {
    data object Initial : StudentScheduleState
    data object Loading : StudentScheduleState
    data class Content(val list: List<Class>) : StudentScheduleState
    data class Failure(val message: String?) : StudentScheduleState
}

class StudentScheduleViewModel(
    private val getStudentScheduleUseCase: GetStudentScheduleUseCase
) : ViewModel() {
    val weekDayList = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ")
    private val monthList = listOf(
        "Января",
        "Февраля",
        "Марта",
        "Апреля",
        "Мая",
        "Июня",
        "Июля",
        "Августа",
        "Сентября",
        "Октября",
        "Ноября",
        "Декабря"
    )
    private val calendar = Calendar.getInstance()
    private val _state: MutableStateFlow<StudentScheduleState> =
        MutableStateFlow(value = StudentScheduleState.Initial)
    val state: StateFlow<StudentScheduleState> = _state

    fun loadSchedule(date: Int) {
        if (_state.value !is StudentScheduleState.Initial)
            return
        viewModelScope.launch {
            _state.value = StudentScheduleState.Loading
            try {
                val list = getStudentScheduleUseCase(date)
                _state.value = StudentScheduleState.Content(list)
            } catch (e: Exception) {
                _state.value = StudentScheduleState.Failure(e.message)
            }
        }
    }

    @Composable
    fun getColor(index: Int, pickedDate: Int): Color {
        return if (pickedDate == index + getFirstWeekDay()) colorResource(id = R.color.purple_500)
        else colorResource(id = R.color.background)
    }

    fun getToday(): String {
        val calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        return "$day ${monthList[month]} $year"
    }

    fun getFirstWeekDay(): Int {
        return calendar.firstDayOfWeek + 1
    }

    fun getTodayInt(): Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

}