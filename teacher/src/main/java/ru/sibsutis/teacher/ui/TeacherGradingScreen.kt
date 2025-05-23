package ru.sibsutis.teacher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.core.ui.LoadingIndicator
import ru.sibsutis.teacher.R
import ru.sibsutis.teacher.presentation.ResponseState

@Composable
fun TeacherGradingScreen(
    responseStates: Map<String, ResponseState>,
    classItem: ClassUI,
    onClick: (String, Int) -> Unit,
    onBack: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (classItem.task?.responses?.isNotEmpty() == true) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.White)
            ) {
                items(classItem.task.responses) {
                    val responseState : ResponseState = responseStates[it.studentId] ?: ResponseState.Initial
                    var responseMark by remember { mutableStateOf(it.mark?.toFloat() ?: 1f)}
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = colorResource(id = R.color.background))
                    ) {
                        val textModifier =
                            Modifier.padding(
                                bottom = 10.dp,
                                start = 5.dp,
                                top = 10.dp,
                                end = 5.dp
                            )
                        Text(
                            modifier = textModifier,
                            text = it.studentId,
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                        Text(
                            modifier = textModifier,
                            text = it.body,
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Slider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp, bottom = 5.dp)
                                .height(30.dp)
                                .background(color = Color.White),
                            value = responseMark,
                            onValueChange = { newValue ->
                                responseMark = newValue
                            },
                            valueRange = 1f..5f,
                            steps = 3,
                            colors = SliderDefaults.colors(
                                thumbColor = colorResource(R.color.blue),
                                activeTrackColor = colorResource(R.color.blue),
                                inactiveTickColor = colorResource(R.color.gray)
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(75.dp)
                                    .padding(top = 5.dp, bottom = 5.dp),
                                onClick = { onClick(it.id, responseMark.toInt()) },
                                colors = ButtonColors(
                                    containerColor = colorResource(id = R.color.blue),
                                    contentColor = ButtonDefaults.buttonColors().contentColor,
                                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = stringResource(id = R.string.rate),
                                    fontSize = 22.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 16.dp)
                            ) {
                                Box(modifier = Modifier.align(Alignment.CenterStart)) {
                                    when (responseState) {
                                        ResponseState.Content -> {
                                            Icon(
                                                modifier = Modifier
                                                    .size(56.dp),
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = stringResource(R.string.success),
                                                tint = colorResource(R.color.blue)
                                            )
                                        }

                                        is ResponseState.Failure -> {
                                            Text(
                                                color = Color.Red,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .size(56.dp),
                                                text = responseState.message,
                                                fontSize = 18.sp
                                            )
                                        }

                                        ResponseState.Initial -> {
                                            Spacer(
                                                modifier = Modifier
                                                    .size(56.dp)
                                            )
                                        }

                                        ResponseState.Loading -> LoadingIndicator(
                                            modifier = Modifier
                                                .size(56.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(75.dp)
                .padding(top = 20.dp),
            onClick = onBack,
            colors = ButtonColors(
                containerColor = colorResource(id = R.color.blue),
                contentColor = ButtonDefaults.buttonColors().contentColor,
                disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
            )
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.back),
                fontSize = 22.sp
            )
        }
    }
}