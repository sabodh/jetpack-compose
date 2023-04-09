package com.virgin.jetpack_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.lifecycleScope
import com.virgin.jetpack_compose.composables.HomeScreen
import com.virgin.jetpack_compose.model.NetworkState
import com.virgin.jetpack_compose.repository.UserRepository
import com.virgin.jetpack_compose.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userRepository = UserRepository("test", "1234")
            val userViewModel = UserViewModel(userRepository)
            lifecycleScope.launchWhenStarted {
                userViewModel.loginState.collect {
                    when (it) {
                        is NetworkState.Initial -> {
                            Log.e("flow:", "flow : Initial")
                        }
                        is NetworkState.Loading -> {
                            Log.e("flow:", "flow : Loading")

                        }
                        is NetworkState.Success -> {
                            Log.e("flow:", "flow : Success")

                        }
                        is NetworkState.Error -> {
                            Log.e("flow:", "flow : Error${it}")

                        }
                        is NetworkState.Result<*> ->{
                            Log.e("flow:", "flow : Result ${it._data.toString()}")

                        }
                    }
                }

            }
            HomeScreen(userViewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val userRepository = UserRepository("test", "1234")
    val userViewModel = UserViewModel(userRepository)
    HomeScreen(userViewModel)
}