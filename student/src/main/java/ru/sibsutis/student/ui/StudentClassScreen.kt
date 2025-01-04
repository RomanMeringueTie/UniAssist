package ru.sibsutis.student.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sibsutis.student.presentation.StudentClassState
import ru.sibsutis.student.presentation.StudentClassViewModel

@Composable
fun StudentClassScreen(viewModel: StudentClassViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    StudentClassScreenImpl(state = state)

}