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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.core.utils.CalendarUtil
import ru.sibsutis.student.R
import ru.sibsutis.student.presentation.StudentScheduleViewModel

@SuppressLint("SimpleDateFormat")
@Composable
fun StudentScheduleScreen(viewModel: StudentScheduleViewModel) {
    val state by viewModel.state.collectAsState()
    val calendar = CalendarUtil()
    var pickedDate by rememberSaveable { mutableIntStateOf(calendar.getTodayInt()) }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.background))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = calendar.getToday(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                calendar.weekDaysIds.forEachIndexed { index, it ->
                    item {
                        val color = getColor(index, pickedDate)
                        LazyColumn(
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    pickedDate = index + calendar.getFirstWeekDay()
                                    viewModel.changePickedDate(index)
                                }
                                .background(color = color)
                                .padding(top = 10.dp, bottom = 20.dp, start = 15.dp, end = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                Text(
                                    text = (index + calendar.getFirstWeekDay()).toString(),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = stringResource(id = it),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
        StudentScheduleScreenImpl(state = state) { }
    }
}