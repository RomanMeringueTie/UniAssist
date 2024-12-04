package ru.sibsutis.student.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.stateIn
import ru.sibsutis.student.R
import ru.sibsutis.student.presentation.StudentScheduleState
import ru.sibsutis.student.presentation.StudentScheduleViewModel
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@Composable
fun StudentScheduleScreen(viewModel: StudentScheduleViewModel) {
    val state by viewModel.state.collectAsState()
    var pickedDate by rememberSaveable { mutableIntStateOf(viewModel.getTodayInt()) }

    LaunchedEffect(Unit) {
        viewModel.loadSchedule(pickedDate)
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.background))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = viewModel.getToday(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                viewModel.weekDayList.forEachIndexed { index, it ->
                    item {
                        val color = viewModel.getColor(index, pickedDate)
                        LazyColumn(
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    pickedDate = index + viewModel.getFirstWeekDay()
                                    viewModel.loadSchedule(pickedDate)
                                }
                                .background(color = color)
                                .padding(top = 10.dp, bottom = 20.dp, start = 15.dp, end = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                Text(
                                    text = (index + viewModel.getFirstWeekDay()).toString(),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = it,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
        when (val s = state) {
            is StudentScheduleState.Content -> {
                StudentScheduleContentComponent(s.list)
            }

            is StudentScheduleState.Failure -> {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = { },
                    text = { Text(text = s.message ?: "Unknown Error") }
                )
            }

            StudentScheduleState.Initial -> {}
            StudentScheduleState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}