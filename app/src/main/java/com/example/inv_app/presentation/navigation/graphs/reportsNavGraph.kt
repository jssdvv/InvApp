package com.example.inv_app.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inv_app.presentation.features.reports.ReportsScreen
import com.example.inv_app.presentation.navigation.FeaturesRoutes

internal sealed class ReportsRoutes(val route: String) {
    object reports : ReportsRoutes("reportsScreen")
}

fun NavGraphBuilder.reportsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ReportsRoutes.reports.route,
        route = FeaturesRoutes.reports.route
    ) {
        composable(ReportsRoutes.reports.route) { ReportsScreen() }
    }
}