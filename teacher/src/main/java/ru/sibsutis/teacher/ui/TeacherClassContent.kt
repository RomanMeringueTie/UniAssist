package ru.sibsutis.teacher.ui

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.teacher.R
import ru.sibsutis.teacher.presentation.TaskState

@Composable
internal fun TeacherClassContent (
    taskState: TaskState,
    classItem: ClassUI,
    isDialogShown: Boolean,
    textValueTitle: String,
    textValueContent: String,
    onValueChangeTitle: (String) -> Unit,
    onValueChangeContent: (String) -> Unit,
    onTaskClick: () -> Unit,
    onAddTask: () -> Unit,
    onResponseClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    if (isDialogShown) {
        TeacherAddTaskDialog(
            taskState = taskState,
            onClick = onAddTask,
            onDismissRequest = onDismissRequest,
            onValueChangeTitle = { newValue: String ->
                onValueChangeTitle(newValue)
            },
            onValueChangeContent = { newValue: String ->
                onValueChangeContent(newValue)
            },
            valueTitle = textValueTitle,
            valueContent = textValueContent
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TeacherClassHeader(classItem = classItem)
        TeacherClassDetails(classItem = classItem)
        if (classItem.task != null) {
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = onResponseClick,
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.blue),
                    contentColor = ButtonDefaults.buttonColors().contentColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
                )
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = stringResource(id = R.string.view_answer),
                    fontSize = 22.sp
                )
            }
        } else {
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = onTaskClick,
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.blue),
                    contentColor = ButtonDefaults.buttonColors().contentColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
                )
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = stringResource(id = R.string.add_task),
                    fontSize = 22.sp
                )
            }
        }
    }
}