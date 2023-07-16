package com.example.inv_app.presentation.navigation.view

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inv_app.presentation.navigation.viewmodel.NavigationViewModel

@Composable
fun ModalNavigationDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController: NavHostController = rememberNavController()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(drawerState, scope, navController)
        },
        modifier = Modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = DrawerDefaults.scrimColor
    ) {
        Scaffold(drawerState, scope, navController)
    }
}