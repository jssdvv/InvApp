package com.example.inv_app.presentation.navigation.view

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.inv_app.presentation.navigation.MyAppNavHost
import kotlinx.coroutines.CoroutineScope

@Composable
fun Scaffold(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                drawerState,
                scope
            )
        },
    ) { paddingValues ->
        MyAppNavHost(paddingValues, navController)
    }
}
