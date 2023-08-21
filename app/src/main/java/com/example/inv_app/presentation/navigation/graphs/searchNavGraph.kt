package com.example.inv_app.presentation.navigation.graphs

import androidx.compose.material3.DrawerState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inv_app.presentation.features.search.DetailScreen
import com.example.inv_app.presentation.features.search.HistoryScreen
import com.example.inv_app.presentation.features.search.ScannerScreen
import com.example.inv_app.presentation.navigation.FeaturesRoutes
import kotlinx.coroutines.flow.MutableStateFlow

internal sealed class ScannerRoutes(val route: String) {
    object scanner : ScannerRoutes("searchScannerScreen")
    object history : ScannerRoutes("searchHistoryScreen")
    object detail : ScannerRoutes("searchDetailScreen")
}

fun NavGraphBuilder.searchNavGraph(
    navController: NavHostController,
    drawerState: DrawerState,
    isGesturesEnabled: MutableStateFlow<Boolean>
) {
    navigation(
        startDestination = ScannerRoutes.scanner.route,
        route = FeaturesRoutes.scanner.route
    ) {
        composable(ScannerRoutes.scanner.route) {
            ScannerScreen(navController, drawerState, isGesturesEnabled)
        }
        composable(ScannerRoutes.history.route) {
            HistoryScreen(navController, drawerState, isGesturesEnabled)
        }
        composable(ScannerRoutes.detail.route) {
            DetailScreen(navController, drawerState, isGesturesEnabled)
        }
    }
}