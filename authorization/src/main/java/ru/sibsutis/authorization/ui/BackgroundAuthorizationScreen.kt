package ru.sibsutis.authorization.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import ru.sibsutis.authorization.presentation.BackgroundAuthorizationViewModel
import ru.sibsutis.core.presentation.State
import ru.sibsutis.core.ui.LoadingIndicator

@Composable
fun BackgroundAuthorizationScreen(
    viewModel: BackgroundAuthorizationViewModel,
    onFailure: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    val context = LocalContext.current

    when (state.value) {

        is State.Content -> {
        }

        is State.Failure -> {
            LaunchedEffect(Unit) {
                Toast.makeText(
                    context,
                    (state.value as State.Failure<Any>).message,
                    Toast.LENGTH_LONG
                ).show()
            }
            onFailure()
        }

        State.Loading -> {
            LoadingIndicator(modifier = Modifier
                .fillMaxSize()
                .background(Color.White))
        }
    }
}