package com.virgin.jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Display the list of 3 Team members using list.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {

    // this is example for stateless composable
    val textFieldValue = remember {
        mutableStateOf("")
    }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    LoginUser(userName = textFieldValue.value,
        password = password.value,
        userNameUpdated = { textFieldValue.value = it },
        passwordUpdated = { password.value = it },
        passwordVisible =  passwordVisibility)
}
@Composable
fun LoginUser(
    userName: String,
    password: String,
    userNameUpdated: (String) -> Unit,
    passwordUpdated: (String) -> Unit,
    passwordVisible: MutableState<Boolean>
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .padding(top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            ImageBox()
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Cricket Scores",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = userName,
                onValueChange = userNameUpdated,
                label = { Text(text = "Username")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(value = password,
                onValueChange = passwordUpdated,
                label = { Text(text = "Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description = if (passwordVisible.value) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(onClick = {passwordVisible.value = !passwordVisible.value} ){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Handle button click */ },
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
        Image(painter = painterResource(id = R.drawable.cricket_icon), contentDescription = null)

    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}