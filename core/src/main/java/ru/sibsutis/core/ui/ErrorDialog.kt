package ru.sibsutis.core.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorDialog(modifier: Modifier, message: String, onRetry: () -> Unit) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { onRetry() }) {
                Text(text = "OK")
            }
        },
        text = { Text(text = message, textAlign = TextAlign.Center) }
    )
}