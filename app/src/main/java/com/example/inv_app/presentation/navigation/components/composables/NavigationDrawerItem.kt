package com.example.inv_app.presentation.navigation.components.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.inv_app.presentation.navigation.components.NavigationItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerItem(
    item: NavigationItems,
    drawerState: DrawerState,
    navController: NavHostController,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentGraphRoute = navBackStackEntry?.destination?.parent?.route

    NavigationDrawerItem(
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unselectedContainerColor = MaterialTheme.colorScheme.surface,
            selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedBadgeColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unselectedBadgeColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        label = {
            Text(
                text = stringResource(id = item.title),
                style = MaterialTheme.typography.labelLarge
            )
        },
        selected = currentGraphRoute == item.route,
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id){
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            coroutineScope.launch { drawerState.close() }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        icon = {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.iconDescription,
                modifier = Modifier.size(size = 24.dp)
            )
        },
        badge = null
    )
}