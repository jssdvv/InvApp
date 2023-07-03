package com.example.inv_app.data.model

sealed class ScreensRoutes(val route: String) {
    object homeScreen : ScreensRoutes("homeScreen")
    object cameraScreens : ScreensRoutes("cameraScreen")
    object cameraPreviewScreens : ScreensRoutes("cameraPreviewScreen")
}