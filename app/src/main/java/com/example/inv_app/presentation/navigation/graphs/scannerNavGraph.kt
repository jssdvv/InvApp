package com.example.inv_app.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inv_app.presentation.features.scanner.ScannerHistoryScreen
import com.example.inv_app.presentation.features.scanner.ScannerScreen
import com.example.inv_app.presentation.navigation.FeaturesRoutes

sealed class ScannerRoutes(val route: String) {
    object scanner : ScannerRoutes("scannerScreen")
    object history : ScannerRoutes("historyScreen")
}

fun NavGraphBuilder.scannerNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ScannerRoutes.scanner.route,
        route = FeaturesRoutes.scanner.route
    ) {
        composable(ScannerRoutes.scanner.route) {
            ScannerScreen(
                onNavigateToScanner = {
                    navController.navigate(ScannerRoutes.scanner.route) {
                        popUpTo(ScannerRoutes.history.route)
                    }
                },
                onNavigateToHistory = {
                    navController.navigate(ScannerRoutes.history.route) {
                        popUpTo(ScannerRoutes.scanner.route)
                    }
                }
            )
        }
        composable(ScannerRoutes.history.route) { ScannerHistoryScreen() }
    }
}