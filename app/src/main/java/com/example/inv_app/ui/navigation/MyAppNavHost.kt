package com.example.inv_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inv_app.data.model.ViewRoutes
import com.example.inv_app.ui.view.CameraScreen
import com.example.inv_app.ui.view.CameraPreviewScreen
import com.example.inv_app.ui.view.HomeView

@Composable
fun MyAppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ViewRoutes.scannerView.route
    ) {
        composable(ViewRoutes.scannerView.route) {
            HomeView(
                onNavigateToCamera = { navController.navigate(ViewRoutes.listsView.route) }
            )
        }
        composable(ViewRoutes.listsView.route) {
            CameraScreen(
                onNavigateToCameraPreview = { navController.navigate(ViewRoutes.reportsView.route) }
            )
        }
        composable(ViewRoutes.reportsView.route) { CameraPreviewScreen() }
    }
}