package com.example.inv_app.data.model

sealed class ScreenViewRoutes(val route: String) {
    object homeScreenView : ScreenViewRoutes("homeView")
    object scannerScreenView : ScreenViewRoutes("scannerView")
    object listsScreenView : ScreenViewRoutes("listsView")
    object reportsScreenView : ScreenViewRoutes("reportsView")
    object formatsScreenView : ScreenViewRoutes("formatsView")
    object contactsScreenView : ScreenViewRoutes("contactsView")
}