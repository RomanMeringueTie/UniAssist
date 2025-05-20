package ru.sibsutis.student.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sibsutis.student.presentation.StudentClassViewModel

@Composable
fun StudentClassScreen(viewModel: StudentClassViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    StudentClassScreenImpl(
        state = state,
        onValueChange = viewModel::changeResponseValue,
        onClick = viewModel::changeResponseDialogStatus,
        onAddResponse = viewModel::onSendResponse,
        onDismissRequest = {
            viewModel.changeResponseDialogStatus()
            viewModel.resetResponse()
        }
    )

}