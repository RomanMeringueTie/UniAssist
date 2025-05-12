package ru.sibsutis.student.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.student.R
import ru.sibsutis.student.data.model.ClassType

@Composable
internal fun StudentClassDetails(
    classItem: ClassUI
) {

    Column(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(10.dp))
            .background(color = colorResource(id = R.color.background)),
    ) {
        val textModifier = Modifier.padding(bottom = 10.dp, start = 5.dp, top = 10.dp, end = 5.dp)
        if (classItem.task == null) {
            Text(
                modifier = textModifier,
                text = stringResource(R.string.empty_task, classItem.teacher),
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        } else {
            Text(
                modifier = textModifier,
                text = classItem.teacher,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                modifier = textModifier,
                text = classItem.task.header,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                modifier = textModifier,
                text = classItem.task.body,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

        }
    }

    if (classItem.task?.responses?.isNotEmpty() == true) {

        Text(
            text = stringResource(R.string.your_responses),
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .height(45.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.background))
        ) {
            items(classItem.task.responses) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                        text = it.body,
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
                            .fillMaxWidth(),
                        text = if (it.mark != null) it.mark.toString()
                        else stringResource(R.string.no_mark),
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview_StudentClassDetails() {
    StudentClassDetails(
        classItem = ClassUI(
            id = "e01733c0-61f3",
            subject = "Схемотехника",
            startTime = "08:00",
            endTime = "09:35",
            type = ClassType.LABORATORY,
            teacher = "Иванов В. П.",
            classroom = "1 - 201",
            task = TaskUI(
                header = "Лабораторная № 3", body = "Сложная лаба", responses = listOf(
                    ResponseUI(
                        body = "Hello, World!", mark = null
                    )
                ).toImmutableList()
            )
        )
    )
}