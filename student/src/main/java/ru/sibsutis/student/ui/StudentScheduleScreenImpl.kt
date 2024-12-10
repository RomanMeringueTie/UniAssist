package ru.sibsutis.student.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.tooling.preview.Preview
import ru.sibsutis.student.presentation.StudentScheduleListState
import ru.sibsutis.student.presentation.StudentScheduleState


@Composable
fun StudentScheduleScreenImpl(
    state: StudentScheduleState,
    onAction: () -> Unit
) {

    val currentState by rememberUpdatedState(newValue = state)

    when (val s = currentState.listState) {
        is StudentScheduleListState.Content -> {
            StudentScheduleContentComponent(s.list)
        }

        is StudentScheduleListState.Failure -> {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = { },
                text = { Text(text = s.message ?: "Unknown Error") }
            )
        }

        is StudentScheduleListState.Loading -> {
            CircularProgressIndicator()
        }
    }
}