package com.example.inv_app.presentation.navigation.view

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    TopAppBar(
        title = { Text(text = "Inventarios") },
        navigationIcon = {
            IconButton(
                onClick = {scope.launch { drawerState.open() }}
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu")
            }
        }
    )
}
