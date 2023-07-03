package com.example.inv_app.ui.navigation.design

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ModalNavigationDrawer(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerContent = {

        },
        modifier = Modifier,
        drawerState = drawerState,
        gesturesEnabled = false,
        scrimColor = DrawerDefaults.scrimColor,
    )
    {
        
    }
}