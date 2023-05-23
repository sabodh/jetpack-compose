package com.virgin.jetpack_compose.presentation

import ExposedDropdownMenu
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import com.virgin.jetpack_compose.domain.CategoryList
import com.virgin.jetpack_compose.domain.PrathistaView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // HomeScreen()
            CategoryList()
           // PrathistaView()
//            ExposedDropdownMenu()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   // HomeScreen()
    CategoryList()
}