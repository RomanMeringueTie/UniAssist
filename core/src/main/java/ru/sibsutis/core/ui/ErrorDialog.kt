package ru.sibsutis.core.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun ErrorDialog(modifier: Modifier, message: String?) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { },
        confirmButton = { },
        text = { Text(text = message ?: "Unknown Error") }
    )
}