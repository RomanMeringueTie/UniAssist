package ru.sibsutis.student.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toPersistentList
import ru.sibsutis.core.ui.ErrorDialog
import ru.sibsutis.core.ui.LoadingComponent
import ru.sibsutis.student.R
import ru.sibsutis.student.presentation.StudentScheduleListState
import ru.sibsutis.student.presentation.StudentScheduleState


@Composable
fun StudentScheduleScreenImpl(
    state: StudentScheduleState,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onClassClicked: (id: Int) -> Unit
) {
    when (state.listState) {
        is StudentScheduleListState.Content -> {
            StudentScheduleContentComponent(
                state.listState.list.toPersistentList(),
                onSwipeLeft,
                onSwipeRight,
                onClassClicked
            )
        }

        is StudentScheduleListState.Failure -> {
            ErrorDialog(modifier = Modifier, message = state.listState.message)
        }

        is StudentScheduleListState.Loading -> {
            LoadingComponent(modifier = Modifier.fillMaxSize())
        }
    }
}