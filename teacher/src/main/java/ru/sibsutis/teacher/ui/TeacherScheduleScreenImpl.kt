package ru.sibsutis.teacher.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.teacher.presentation.TeacherScheduleListState
import ru.sibsutis.teacher.presentation.TeacherScheduleState

@Composable
fun TeacherScheduleScreenImpl(
    state: TeacherScheduleState,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onRetry: () -> Unit
) {
    when (state.listState) {
        is TeacherScheduleListState.Content -> {
            TeacherScheduleContent(
                list = state.listState.list.toImmutableList(),
                onSwipeRight = onSwipeRight,
                onSwipeLeft = onSwipeLeft
            )
        }

        is TeacherScheduleListState.Failure -> ErrorDialog(
            modifier = Modifier,
            message = state.listState.message,
            onDismissRequest = { },
            onRetry = onRetry
        )

        is TeacherScheduleListState.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
    }
}