package com.example.inv_app.data.model

sealed class ViewRoutes(val route: String) {
    object homeView : ViewRoutes("homeView")
    object scannerView : ViewRoutes("scannerView")
    object listsView : ViewRoutes("listsView")
    object reportsView : ViewRoutes("reportsView")
    object formatsView : ViewRoutes("formatsView")
    object contactsView : ViewRoutes("contactsView")
}