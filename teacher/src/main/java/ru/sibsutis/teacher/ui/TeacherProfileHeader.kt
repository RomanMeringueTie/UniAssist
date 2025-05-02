package ru.sibsutis.teacher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun TeacherProfileHeader() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = UserData.fullName ?: stringResource(R.string.unknown_user),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(
                id = R.color.blue
            )
        )
        Text(
            text = stringResource(R.string.teacher),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        Text(
            text = UserData.unit ?: stringResource(R.string.unknown_department),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}