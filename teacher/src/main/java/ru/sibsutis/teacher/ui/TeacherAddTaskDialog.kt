package ru.sibsutis.teacher.ui

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
import ru.sibsutis.teacher.R
import ru.sibsutis.teacher.presentation.TaskState

@Composable
fun TeacherAddTaskDialog(
    taskState: TaskState,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onValueChangeHeader: (String) -> Unit,
    onValueChangeBody: (String) -> Unit,
    valueHeader: String,
    valueBody: String
) {
    AlertDialog (
        modifier = Modifier.padding(10.dp),
        text = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    modifier = Modifier.height(50.dp)
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        value = valueHeader,
                        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        onValueChange = { it: String ->
                            onValueChangeHeader(it)
                        },
                        decorationBox = { innerTextField ->
                            if (valueHeader.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.task_title),
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                )
                            }
                            innerTextField()
                        }
                    )
                }
                Card(
                    modifier = Modifier.height(250.dp)
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        value = valueBody,
                        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        onValueChange = { it: String ->
                            onValueChangeBody(it)
                        },
                        decorationBox = { innerTextField ->
                            if (valueBody.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.task_text),
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
            if (taskState !is TaskState.Loading) {
                onDismissRequest()
            }
        },
        confirmButton = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .height(50.dp),
                    onClick = {
                        if (taskState !is TaskState.Loading) onClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.blue)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.send_task),
                        modifier = Modifier.padding(6.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
                when (taskState) {
                    TaskState.Content -> {
                        Icon(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .size(56.dp),
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = stringResource(R.string.success),
                            tint = colorResource(R.color.blue)
                        )
                    }

                    is TaskState.Failure -> {
                        Text(
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .size(56.dp),
                            text = taskState.message,
                            fontSize = 18.sp
                        )
                    }

                    TaskState.Initial -> {
                        Spacer(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .size(56.dp)
                        )
                    }

                    TaskState.Loading -> LoadingIndicator(
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
private fun Preview_TeacherAddResponseDialog() {
    TeacherAddTaskDialog(
        taskState = TaskState.Loading,
        onClick = {},
        onDismissRequest = {},
        onValueChangeHeader = {},
        onValueChangeBody = {},
        valueHeader = "Создание сетевого приложения",
        valueBody = "Создать клиентское приложени для соединения с сервером по протоколу FTP"
    )
}