package ru.sibsutis.teacher.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sibsutis.teacher.presentation.TeacherClassViewModel

@Composable
fun TeacherClassScreen(viewModel: TeacherClassViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TeacherClassScreenImpl(
        state = state,
        onValueChangeHeader = viewModel::changeTaskValueHeader,
        onValueChangeBody = viewModel::changeTaskValueBody,
        onClick = viewModel::changeTaskDialogStatus,
        onAddTask = viewModel::onSendTask,
        onDismissRequest = {
            viewModel.changeTaskDialogStatus()
            viewModel.resetTask()
        }
    )
}