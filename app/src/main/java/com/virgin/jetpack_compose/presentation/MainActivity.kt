package com.virgin.jetpack_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import com.virgin.jetpack_compose.domain.CategoryList


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // HomeScreen()
            CategoryList()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   // HomeScreen()
    CategoryList()
}