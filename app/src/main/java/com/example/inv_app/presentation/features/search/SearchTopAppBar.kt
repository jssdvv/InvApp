package com.example.inv_app.presentation.features.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.inv_app.presentation.navigation.graphs.ScannerRoutes
import kotlinx.coroutines.launch

data class SearchActionItem(
    val icon: Painter,
    val description: String,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    navController: NavHostController,
    drawerState: DrawerState
) {
    val coroutineScope = rememberCoroutineScope()
    val actions = listOf(
        SearchActionItem(
            icon = painterResource(id = com.example.inv_app.R.drawable.outlined_history_icon),
            description = "",
            onClick = { navController.navigate(ScannerRoutes.history.route) }
        ),
        SearchActionItem(
            icon = painterResource(id = com.example.inv_app.R.drawable.outlined_more_vertical_icon),
            description = "more options",
            onClick = { }
        )
    )
    TopAppBar(
        title = {
            Text(
                text = "Inventarios",
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = Modifier,
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutineScope.launch { drawerState.open() }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            actions.forEach { searchActionItem ->
                IconButton(
                    onClick = searchActionItem.onClick
                ) {
                    Icon(
                        painter = searchActionItem.icon,
                        contentDescription = searchActionItem.description,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}