package ru.sibsutis.authorization.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.authorization.R
import ru.sibsutis.authorization.presentation.AuthorizationViewModel
import ru.sibsutis.authorization.presentation.LoginState
import ru.sibsutis.core.ui.LoadingIndicator

@Composable
fun AuthorizationScreen(
    viewModel: AuthorizationViewModel,
    onClick: () -> Unit,
) {

    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.background))
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                imageVector = ImageVector.vectorResource(R.drawable.uni_assist_logo),
                contentDescription = "UniAssistLogo"
            )
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = stringResource(R.string.login_header),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue)
            )
            InputField(
                imageVector = Icons.Outlined.AccountCircle,
                hint = stringResource(R.string.enter_login),
                value = state.login,
                onValueChange = {
                    viewModel.setLogin(it)
                }
            )
            InputField(
                imageVector = Icons.Outlined.Lock,
                hint = stringResource(R.string.enter_password),
                value = state.password,
                onValueChange = {
                    viewModel.setPassword(it)
                },
                isAbleToHide = true,
                isHidden = state.isPasswordShown,
                onVisibilityChange = { viewModel.changePasswordVisibility() }
            )
            LoadingOrError(
                state = state.state,
                onContent = onClick
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    viewModel.authorize()
                },
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.blue),
                    contentColor = ButtonDefaults.buttonColors().contentColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = stringResource(R.string.login),
                    fontSize = 22.sp
                )
            }
        }
    }
}

@Composable
private fun LoadingOrError(state: LoginState, onContent: () -> Unit) {
    when (state) {
        LoginState.Loading -> {
            LoadingIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }

        is LoginState.Content -> {
            Spacer(modifier = Modifier.height(40.dp))
            onContent()
        }

        is LoginState.Failure -> {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                text = state.message,
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        LoginState.Initial -> {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun InputField(
    imageVector: ImageVector,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isAbleToHide: Boolean = false,
    isHidden: Boolean = true,
    onVisibilityChange: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Black,
        ),
        border = BorderStroke(width = 2.dp, color = colorResource(R.color.blue)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(25.dp),
                imageVector = imageVector,
                contentDescription = "inputFieldIcon"
            )
            BasicTextField(
                modifier = Modifier.padding(start = 5.dp),
                value = value,
                textStyle = TextStyle(fontSize = 14.sp),
                onValueChange = { it: String ->
                    onValueChange(it)
                },
                visualTransformation = if (isHidden) VisualTransformation.None else PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            text = hint,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }
                    innerTextField()
                }
            )
            if (isAbleToHide) {
                HideButton(
                    isHidden = isHidden,
                    onVisibilityChange = onVisibilityChange
                )
            }
        }
    }
}

@Composable
private fun HideButton(isHidden: Boolean, onVisibilityChange: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Image(
            modifier = Modifier
                .clickable {
                    onVisibilityChange()
                }
                .size(25.dp),
            alignment = Alignment.CenterEnd,
            imageVector = if (isHidden) ImageVector.vectorResource(R.drawable.not_visible) else ImageVector.vectorResource(
                R.drawable.visible
            ),
            contentDescription = "hidePassword"
        )
    }
}