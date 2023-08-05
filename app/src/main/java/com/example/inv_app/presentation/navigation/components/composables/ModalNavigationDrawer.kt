package com.example.inv_app.presentation.navigation.components.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ModalNavigationDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    navController: NavHostController = rememberNavController(),
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerState,
                navController
            )
        },
        modifier = Modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = DrawerDefaults.scrimColor
    ) {
        com.example.inv_app.presentation.navigation.components.Scaffold(drawerState, navController)
    }
}