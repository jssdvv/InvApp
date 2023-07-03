package com.example.inv_app.ui.navigation.design

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.inv_app.data.model.ScreensRoutes

sealed class NavigationItems(
    val title: String,
    val icon: ImageVector,
    val iconDescription: String,
    val route: String
) {
    object homeItem : NavigationItems(
        title = "Inicio",
        icon = Icons.Default.Home,
        iconDescription = "Home icon",
        route = ScreensRoutes.homeScreen.route
    )
    object cameraItem : NavigationItems(
        title = "Camara",
        icon = Icons.Default.Info,
        iconDescription = "Camera icon",
        route = ScreensRoutes.cameraScreens.route
    )
}