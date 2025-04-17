package ru.sibsutis.student.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import ru.sibsutis.authorization.data.model.UserData
import ru.sibsutis.student.R

@Composable
fun StudentProfileScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        StudentProfileHeader()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            colors = CardColors(
                containerColor = colorResource(R.color.background),
                contentColor = colorResource(R.color.black),
                disabledContainerColor = colorResource(R.color.background),
                disabledContentColor = colorResource(R.color.black)
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                text = stringResource(R.string.your_qr_code),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }

        Image(
            modifier = Modifier
                .fillMaxSize(0.7f),
            painter = rememberQrCodePainter(UserData.token ?: ""),
            contentDescription = "QR code",
        )
    }

}