package ru.sibsutis.teacher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.teacher.R

@Composable
internal fun TeacherClassDetails(classItem: ClassUI) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(10.dp))
            .background(color = colorResource(id = R.color.background))
    ) {
        val textModifier =
            Modifier.padding(bottom = 10.dp, start = 5.dp, top = 10.dp, end = 5.dp)
        if (classItem.task == null) {
            Text(
                modifier = textModifier,
                text = classItem.group,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        } else {
            Text(
                modifier = textModifier,
                text = UserData.fullName ?: stringResource(R.string.unknown_user),
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
}