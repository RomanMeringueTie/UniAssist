package ru.sibsutis.student.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.sibsutis.core.presentation.State
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.student.presentation.StudentClassState

@Composable
internal fun StudentClassScreenImpl(
    state: StudentClassState,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    onAddResponse: (String) -> Unit,
    onDismissRequest: () -> Unit
) {

    when (state.classState) {
        is State.Content -> {
            StudentClassContent(
                responseState = state.responseState,
                classItem = state.classState.content,
                isDialogShown = state.isDialogShown,
                textValue = state.responseValue,
                onValueChange = onValueChange,
                onClick = onClick,
                onAddResponse = onAddResponse,
                onDismissRequest = onDismissRequest
            )
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