package com.example.inv_app.presentation.features.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SearchScaffold(
    navController: NavHostController,
    drawerState: DrawerState,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = { SearchTopAppBar(navController, drawerState) }
    ) { paddingValues ->
        content(paddingValues)
    }
}