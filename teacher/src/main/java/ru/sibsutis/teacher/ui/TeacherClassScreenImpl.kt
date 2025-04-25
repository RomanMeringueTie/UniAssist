package ru.sibsutis.teacher.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sibsutis.core.presentation.State
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator

@Composable
internal fun TeacherClassScreenImpl(state: State<ClassUI>) {
    when (state) {
        is State.Content -> {
            TeacherClassContent(classItem = state.content)
        }

        is State.Failure -> {
            ErrorDialog(
                modifier = Modifier,
                message = state.message,
                onDismissRequest = {},
                onRetry = {}
            )
        }

        State.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}