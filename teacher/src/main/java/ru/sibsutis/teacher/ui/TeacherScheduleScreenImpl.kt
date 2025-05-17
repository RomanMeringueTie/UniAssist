package ru.sibsutis.teacher.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.core.presentation.State
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.teacher.presentation.TeacherScheduleState

@Composable
internal fun TeacherScheduleScreenImpl(
    state: TeacherScheduleState,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onRetry: () -> Unit,
    onItemClick: (String) -> Unit
) {
    when (state.listState) {
        is State.Content -> {
            TeacherScheduleContent(
                list = state.listState.content.toImmutableList(),
                onSwipeRight = onSwipeRight,
                onSwipeLeft = onSwipeLeft,
                onItemClick = onItemClick
            )
        }

        is State.Failure -> ErrorDialog(
            modifier = Modifier,
            message = state.listState.message,
            onDismissRequest = { },
            onRetry = onRetry
        )

        is State.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
    }
}