package ru.sibsutis.teacher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.sibsutis.core.presentation.State
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.teacher.presentation.TeacherClassState

@Composable
internal fun TeacherClassScreenImpl(
    state: TeacherClassState,
    onValueChangeHeader: (String) -> Unit,
    onValueChangeBody: (String) -> Unit,
    onTaskClick: () -> Unit,
    onAddTask: () -> Unit,
    onResponseClick: () -> Unit,
    onSendMark: (String, Int) -> Unit,
    onDismissRequest : () -> Unit
) {
    when (state.classState) {
        is State.Content -> {
            if (state.isResponseShown) {
                TeacherGradingScreen(
                    responseState = state.responseState,
                    classItem = state.classState.content,
                    onClick = onSendMark,
                    onBack = onResponseClick
                )
            }
            else {
                TeacherClassContent(
                    taskState = state.taskState,
                    classItem = state.classState.content,
                    isDialogShown = state.isDialogShown,
                    textValueHeader = state.taskValueHeader,
                    textValueBody = state.taskValueBody,
                    onValueChangeHeader = onValueChangeHeader,
                    onValueChangeBody = onValueChangeBody,
                    onTaskClick = onTaskClick,
                    onAddTask = onAddTask,
                    onResponseClick = onResponseClick,
                    onDismissRequest = onDismissRequest
                )
            }
        }

        is State.Failure -> {
            ErrorDialog(
                modifier = Modifier,
                message = state.classState.message,
                onDismissRequest = {},
                onRetry = {}
            )
        }

        State.Loading -> {
            LoadingIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }
    }
}