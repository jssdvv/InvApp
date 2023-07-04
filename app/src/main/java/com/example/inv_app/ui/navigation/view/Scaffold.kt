package com.example.inv_app.ui.navigation.view

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.inv_app.ui.navigation.MyAppNavHost
import kotlinx.coroutines.CoroutineScope

@Composable
fun Scaffold(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier,
        topBar = { TopAppBar(
            drawerState,
            scope
        ) },
    ) {
        MyAppNavHost(navController)
    }
}
