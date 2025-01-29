package ru.sibsutis.student.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.student.presentation.StudentClassState

@Composable
fun StudentClassScreenImpl(state: StudentClassState) {

    when (state) {
        is StudentClassState.Content -> {
            StudentClassContentComponent(classItem = state.classItem)
        }

        is StudentClassState.Failure -> {
            ErrorDialog(
                modifier = Modifier,
                message = state.message ?: "Unknown Error",
                onDismissRequest = {},
                onRetry = {})
        }

        StudentClassState.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}