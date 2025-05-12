package ru.sibsutis.student.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.student.R
import ru.sibsutis.student.presentation.ResponseState

@Composable
fun StudentAddResponseDialog(
    responseState: ResponseState,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onValueChange: (String) -> Unit,
    value: String
) {
    AlertDialog(
        modifier = Modifier.padding(10.dp),
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.height(300.dp)
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        value = value,
                        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        onValueChange = { it: String ->
                            onValueChange(it)
                        },
                        decorationBox = { innerTextField ->
                            if (value.isEmpty()) {
                                Text(
                                    text = "Ваш ответ на задание",
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            }
        },
        onDismissRequest = {
            if (responseState !is ResponseState.Loading) {
                onDismissRequest()
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .height(50.dp),
                    onClick = {
                        if (responseState !is ResponseState.Loading) onClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.blue)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.send_response),
                        modifier = Modifier
                            .padding(6.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
                when (responseState) {
                    ResponseState.Content -> {
                        Icon(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .size(56.dp),
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = stringResource(R.string.success),
                            tint = colorResource(R.color.blue)
                        )
                    }

                    is ResponseState.Failure -> {
                        Text(
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .size(56.dp),
                            text = responseState.message,
                            fontSize = 18.sp
                        )
                    }

                    ResponseState.Initial -> {
                        Spacer(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .size(56.dp)
                        )
                    }

                    ResponseState.Loading -> LoadingIndicator(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .size(56.dp)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun Preview_StudentAddResponseDialog() {
    StudentAddResponseDialog(
        responseState = ResponseState.Loading,
        onClick = {},
        onDismissRequest = {},
        onValueChange = {},
        value = "Сделал модель процессора, за исключением модуля АЛУ"
    )
}