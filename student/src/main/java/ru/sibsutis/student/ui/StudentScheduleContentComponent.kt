package ru.sibsutis.student.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import ru.sibsutis.student.R
import ru.sibsutis.student.data.model.Class

@Composable
fun StudentScheduleContentComponent(
    list: PersistentList<Class>,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onClassClicked: (id: Int) -> Unit
) {
    var offset by remember { mutableFloatStateOf(0f) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset = 0f },
                    onDragEnd = {
                        if (offset < -300) {
                            onSwipeLeft()
                        } else if (offset > 300)
                            onSwipeRight()
                    }
                ) { _, dragAmount ->
                    offset += dragAmount
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) {
            Column(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.background))
                    .clickable { onClassClicked(it.id) },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 20.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = it.startTime.toString(), fontSize = 12.sp)
                        Text(text = it.endTime.toString(), fontSize = 12.sp)
                    }
                    VerticalDivider(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        color = Color.Black,
                        thickness = 2.dp
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${it.subject} (${it.teacher})",
                            color = colorResource(id = R.color.blue),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Практика",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                text = it.classroom,
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                            if (it.task != null) {
                                VerticalDivider(
                                    modifier = Modifier.padding(start = 3.dp, end = 3.dp),
                                    color = Color.Black,
                                    thickness = 1.dp
                                )
                                Text(
                                    text = it.task.header,
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}