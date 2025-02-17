package ru.sibsutis.student.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.student.presentation.State

@Composable
internal fun StudentClassScreenImpl(state: State<ClassUI>) {

    when (state) {
        is State.Content -> {
            StudentClassContent(classItem = state.content)
        }

        is State.Failure -> {
            ErrorDialog(
                modifier = Modifier,
                message = state.message,
                onDismissRequest = {},
                onRetry = {})
        }

        State.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }

    }
}