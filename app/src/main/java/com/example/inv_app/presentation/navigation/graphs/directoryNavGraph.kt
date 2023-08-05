package com.example.inv_app.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inv_app.presentation.features.DirectoryScreen
import com.example.inv_app.presentation.navigation.FeaturesRoutes

internal sealed class DirectoryRoutes(val route: String) {
    object directory : DirectoryRoutes("directoryScreen")
}

fun NavGraphBuilder.directoryNavGraph(navController: NavHostController) {
    navigation(
        startDestination = DirectoryRoutes.directory.route,
        route = FeaturesRoutes.directory.route
    ) {
        composable(DirectoryRoutes.directory.route) { DirectoryScreen() }
    }
}