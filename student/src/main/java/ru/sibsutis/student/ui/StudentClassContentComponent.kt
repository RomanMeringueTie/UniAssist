package ru.sibsutis.student.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.student.R

@Composable
fun StudentClassContentComponent(classItem: ClassUI) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.background))
                .padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = classItem.subject,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(
                    id = R.color.blue
                )
            )
            Text(
                text = "${classItem.startTime} - ${classItem.endTime}",
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                text = "Практика",
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                text = classItem.classroom,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.background)),
        ) {
            val textModifier =
                Modifier.padding(bottom = 10.dp, start = 5.dp, top = 10.dp, end = 5.dp)
            if (classItem.taskHeader == null) {
                Text(
                    modifier = textModifier,
                    text = "Преподаватель ${classItem.teacher} не выложил задание на это занятие",
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
                    text = classItem.taskHeader,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                Text(
                    modifier = textModifier,
                    text = "Очень сложное задание",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        }
        if (classItem.taskHeader != null)
            Button(
                onClick = { },
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.blue),
                    contentColor = ButtonDefaults.buttonColors().contentColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
                ),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Добавить ответ",
                    fontSize = 22.sp
                )
            }
    }
}