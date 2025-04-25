package ru.sibsutis.teacher.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.teacher.R
import ru.sibsutis.teacher.data.model.ClassType

@Composable
internal fun TeacherScheduleClassItem(item: ClassUI) {
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
            Text(text = item.startTime, fontSize = 12.sp)
            Text(text = item.endTime, fontSize = 12.sp)
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
                text = "${item.subject} (${item.group})",
                color = colorResource(id = R.color.blue),
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = convertType(typeModel = item.type),
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = item.classroom,
                    color = Color.Black,
                    fontSize = 12.sp
                )
                if (item.task != null) {
                    VerticalDivider(
                        modifier = Modifier.padding(start = 3.dp, end = 3.dp),
                        color = Color.Black,
                        thickness = 1.dp
                    )
                    Text(
                        text = item.task.header,
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}