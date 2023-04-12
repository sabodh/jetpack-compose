package com.virgin.jetpack_compose.presentation.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.virgin.jetpack_compose.R
import com.virgin.jetpack_compose.model.NetworkState
import com.virgin.jetpack_compose.presentation.LoginFormEvent
import com.virgin.jetpack_compose.viewmodel.UserViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect

@Composable
fun HomeScreen(
) {
    LoginUser()
}

@Composable
fun LoginUser(
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()

    ) {
        val viewModel = viewModel<UserViewModel>()
        val uiState = viewModel.uiState
        val context = LocalContext.current
        val loginState = viewModel.loginState.collectAsState()
        LaunchedEffect(key1 = context) {
//            viewModel.loginState.collect {
                when (loginState.value) {
                    is NetworkState.Initial -> {
                        Log.e("flow:", "flow : Initial")
                    }
                    is NetworkState.Loading -> {
                        Log.e("flow:", "flow : Loading")

                    }
                    is NetworkState.Error -> {
                        Log.e("flow:", "flow : Error")
//                        Toast.makeTextText(context,"hello",Toast.LENGTH_SHORT).show()
//
                        //MySnackbar(message = "hello")
                    }
                    is NetworkState.Success<*> -> {
                        //Log.e("flow:", "flow : Result ${it._data.toString()}")
                       // Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()

                    }
//                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .padding(top = 70.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            ImageBox()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Cricket Scores",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = uiState.userName,
                onValueChange = {
                    viewModel.onEvent(LoginFormEvent.UsernameChanged(it))
                },
                isError = uiState.userNameError != null,
                label = {
                    Text(text = "Username")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            uiState.userNameError?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = it,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = uiState.password,
                onValueChange = {
                    viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
                },
                isError = uiState.passwordError != null,
                label = { Text(text = "Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (uiState.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (uiState.passwordVisibility)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description =
                        if (uiState.passwordVisibility) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(onClick = {
                        viewModel.onEvent(LoginFormEvent.PasswordVisibilityChanged(uiState.passwordVisibility))
                    }) {
                        Icon(imageVector = image, description)
                    }
                }
            )

            uiState.passwordError?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = it,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.onEvent(LoginFormEvent.Submit)
                },
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("LogIn")
            }


        }
    }
}

@Composable
fun ImageBox() {
    Surface(
        modifier = Modifier
            .height(250.dp)
            .width(250.dp)
            .background(Color.White)

    ) {
        Image(
            painter = painterResource(id = R.drawable.cricket_icon),
            contentDescription = null
        )

    }
}
@Composable
fun MySnackbar(message: String) {
    Surface {
        Snackbar {
            Text(message)
        }
    }
}