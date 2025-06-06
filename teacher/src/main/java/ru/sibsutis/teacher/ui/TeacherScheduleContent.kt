package ru.sibsutis.teacher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ru.sibsutis.teacher.R

@Composable
internal fun TeacherScheduleContent(
    list: ImmutableList<ClassUI>,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onItemClick: (String) -> Unit
) {
    var offset by remember { mutableFloatStateOf(0f) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset = 0f},
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
        item {
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
        items(list) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = colorResource(id = R.color.background))
                    .clickable { onItemClick(it.id) }
            ) {
                TeacherScheduleClassItem(item = it)
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}