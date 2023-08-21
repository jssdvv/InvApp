package com.example.inv_app.presentation.navigation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inv_app.presentation.navigation.NavigationHost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun ModalNavigationDrawer(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    isGesturesEnabled: MutableStateFlow<Boolean> = remember { MutableStateFlow(true) }
) {
    ModalNavigationDrawer(
        drawerContent = { ModalDrawerSheet(navController, drawerState) },
        modifier = Modifier,
        drawerState = drawerState,
        gesturesEnabled = isGesturesEnabled.asStateFlow().collectAsState().value,
        scrimColor = DrawerDefaults.scrimColor,
        content = { NavigationHost(navController, drawerState, isGesturesEnabled) }
    )
}