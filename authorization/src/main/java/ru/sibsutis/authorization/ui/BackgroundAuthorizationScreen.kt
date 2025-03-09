package ru.sibsutis.authorization.ui

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ru.sibsutis.authorization.presentation.BackgroundAuthorizationViewModel
import ru.sibsutis.core.presentation.State
import ru.sibsutis.core.ui.LoadingIndicator

@Composable
fun BackgroundAuthorizationScreen(
    viewModel: BackgroundAuthorizationViewModel,
    onContent: () -> Unit,
    onFailure: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    when (state.value) {

        is State.Content -> {
            onContent()
        }

        is State.Failure -> {
            Toast.makeText(
                LocalContext.current,
                (state.value as State.Failure<Any>).message,
                Toast.LENGTH_LONG
            ).show()
            onFailure()
        }

        State.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}