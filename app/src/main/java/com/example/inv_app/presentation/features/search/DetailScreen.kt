package com.example.inv_app.presentation.features.search

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun DetailScreen(
    navController: NavHostController,
    drawerState: DrawerState,
    isGesturesEnabled: MutableStateFlow<Boolean>
) {
    SearchScaffold(
        navController = navController,
        drawerState = drawerState
    ) { paddingValues ->

    }
}