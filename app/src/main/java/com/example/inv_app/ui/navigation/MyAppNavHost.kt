package com.example.inv_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inv_app.data.model.ScreensRoutes
import com.example.inv_app.ui.view.CameraScreen
import com.example.inv_app.ui.view.CameraPreviewScreen
import com.example.inv_app.ui.view.HomeView

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.homeScreen.route
    ) {
        composable(ScreensRoutes.homeScreen.route) {
            HomeView(
                onNavigateToCamera = { navController.navigate(ScreensRoutes.cameraScreens.route) }
            )
        }
        composable(ScreensRoutes.cameraScreens.route) {
            CameraScreen(
                onNavigateToCameraPreview = { navController.navigate(ScreensRoutes.cameraPreviewScreens.route) }
            )
        }
        composable(ScreensRoutes.cameraPreviewScreens.route) { CameraPreviewScreen() }
    }
}