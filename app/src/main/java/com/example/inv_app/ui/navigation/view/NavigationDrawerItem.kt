package com.example.inv_app.ui.navigation.view

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import  androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerItem(
    item: NavigationItems,
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavHostController
) {
    NavigationDrawerItem(
        label = { Text(text = item.title) },
        selected = false,
        onClick = {
            navController.navigate(item.route)
            scope.launch { drawerState.close() }
        },
        modifier = Modifier,
        icon = { Icon(imageVector = item.icon, contentDescription = item.description) },
        badge = null
    )
}