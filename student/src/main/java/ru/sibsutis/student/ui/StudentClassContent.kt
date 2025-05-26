package ru.sibsutis.student.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.student.R
import ru.sibsutis.student.data.model.ClassType
import ru.sibsutis.student.data.model.Response
import ru.sibsutis.student.data.model.Task
import ru.sibsutis.student.presentation.ResponseState

@Composable
internal fun StudentClassContent(
    responseState: ResponseState,
    classItem: ClassUI,
    isDialogShown: Boolean,
    textValue: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    onAddResponse: (String) -> Unit,
    onDismissRequest: () -> Unit
) {

    if (isDialogShown && classItem.task != null) {

        StudentAddResponseDialog(
            taskId = classItem.task.id,
            responseState = responseState,
            onClick = onAddResponse,
            onDismissRequest = onDismissRequest,
            onValueChange = { newValue: String ->
                onValueChange(newValue)
            },
            value = textValue
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StudentClassHeader(classItem = classItem)
        StudentClassDetails(classItem = classItem)
        if (classItem.task != null && classItem.response == null)
            Button(
                onClick = onClick,
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
                    text = stringResource(R.string.add_answer),
                    fontSize = 22.sp
                )
            }
    }
}

@Composable
@Preview
private fun Preview_StudentClassContent() {
    StudentClassContent(
        responseState = ResponseState.Initial,
        classItem = ClassUI(
            id = "e01733c0-61f3",
            subject = "Схемотехника",
            startTime = "08:00",
            endTime = "09:35",
            type = ClassType.LABORATORY,
            teacher = "Иванов В. П.",
            classroom = "1 - 201",
            task = Task(
                id = "",
                header = "Лабораторная № 3",
                body = "Сложная лаба"
            ),
            response = Response(
                id = "",
                body = "Hello, World!",
                mark = null
            ),
        ),
        isDialogShown = false,
        textValue = "",
        onValueChange = {},
        onClick = {},
        onAddResponse = {},
        onDismissRequest = {}
    )
}
