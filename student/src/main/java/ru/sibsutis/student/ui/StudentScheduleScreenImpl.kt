package ru.sibsutis.student.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.toImmutableList
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.student.presentation.StudentScheduleListState
import ru.sibsutis.student.presentation.StudentScheduleState


@Composable
fun StudentScheduleScreenImpl(
    state: StudentScheduleState,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onRetry: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    when (state.listState) {
        is StudentScheduleListState.Content -> {
            StudentScheduleContent(
                list = state.listState.list.toImmutableList(),
                onSwipeLeft = onSwipeLeft,
                onSwipeRight = onSwipeRight,
                onItemClick = onItemClick
            )
        }

        is StudentScheduleListState.Failure -> ErrorDialog(
            modifier = Modifier,
            message = state.listState.message,
            onDismissRequest = { },
            onRetry = onRetry
        )

        is StudentScheduleListState.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
    }
}