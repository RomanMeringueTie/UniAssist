package ru.sibsutis.student.ui

import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import ru.sibsutis.core.utils.CalendarUtil
import ru.sibsutis.student.R
import ru.sibsutis.student.presentation.StudentScheduleViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun StudentScheduleScreen(viewModel: StudentScheduleViewModel, onClassClicked: (id: Int) -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.background))
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = CalendarUtil.getToday(), fontSize = 20.sp, fontWeight = FontWeight.ExtraBold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val weekDays = CalendarUtil.getWeekDays()
                weekDays.forEach {
                    val colorId =
                        if (it == viewModel.state.value.date)
                            R.color.blue else R.color.background
                    var modifier = Modifier.clip(RoundedCornerShape(5.dp))
                    if (CalendarUtil.isToday(it)) modifier = modifier.then(
                        modifier.border(
                            2.dp,
                            colorResource(id = R.color.blue),
                            RoundedCornerShape(5.dp)
                        )
                    )
                    modifier = modifier.then(
                        modifier
                            .clickable {
                                viewModel.changePickedDate(it)
                            }
                            .background(color = colorResource(id = colorId))
                            .padding(top = 10.dp, bottom = 20.dp, start = 15.dp, end = 15.dp))
                    LazyColumn(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Text(
                                text = it.dayOfMonth.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = weekDaysIds[it.dayOfWeek.value - 1]),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
        StudentScheduleScreenImpl(
            state = state,
            { viewModel.changePickedDate(state.date.plus(1, DateTimeUnit.DAY)) },
            { viewModel.changePickedDate(state.date.minus(1, DateTimeUnit.DAY)) },
            onClassClicked
        )
    }
}