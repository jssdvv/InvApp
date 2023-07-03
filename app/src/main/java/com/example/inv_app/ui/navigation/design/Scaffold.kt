package com.example.inv_app.ui.navigation.design

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.inv_app.ui.navigation.MyAppNavHost

@Composable
fun Scaffold() {
    Scaffold(
        modifier = Modifier,
        topBar = { TopAppBar() }
    ) {
        MyAppNavHost()
    }
}
