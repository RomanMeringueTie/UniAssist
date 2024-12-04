package ru.sibsutis.student.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.sibsutis.student.data.model.Class

@Composable
fun StudentScheduleContentComponent(list: List<Class>) {
    LazyColumn {
        items(list) {
            Text(text = "${it.id}")
        }
    }
}