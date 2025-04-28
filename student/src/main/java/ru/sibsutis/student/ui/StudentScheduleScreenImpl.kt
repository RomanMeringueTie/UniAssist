package ru.sibsutis.student.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.core.presentation.State
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.student.presentation.StudentScheduleState


@Composable
internal fun StudentScheduleScreenImpl(
    state: StudentScheduleState,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onRetry: () -> Unit,
    onItemClick: (String) -> Unit
) {
    when (state.listState) {
        is State.Content -> {
            StudentScheduleContent(
                list = state.listState.content.toImmutableList(),
                onSwipeLeft = onSwipeLeft,
                onSwipeRight = onSwipeRight,
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