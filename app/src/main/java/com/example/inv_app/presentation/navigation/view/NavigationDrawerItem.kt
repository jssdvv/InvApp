package com.example.inv_app.presentation.navigation.view

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import  androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.inv_app.presentation.navigation.NavigationItems
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
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
                restoreState = true
            }
            scope.launch { drawerState.close() }
        },
        modifier = Modifier,
        icon = {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.description
            )
        },
        badge = null
    )
}