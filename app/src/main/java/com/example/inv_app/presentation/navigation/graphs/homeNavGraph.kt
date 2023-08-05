package com.example.inv_app.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inv_app.presentation.features.home.HomeScreen
import com.example.inv_app.presentation.navigation.FeaturesRoutes

internal sealed class HomeRoutes(val route: String) {
    object home : HomeRoutes("homeScreen")
}

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        startDestination = HomeRoutes.home.route,
        route = FeaturesRoutes.home.route
    ) {
        composable(HomeRoutes.home.route) { HomeScreen() }
    }
}