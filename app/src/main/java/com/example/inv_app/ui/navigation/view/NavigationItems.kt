package com.example.inv_app.ui.navigation.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.inv_app.data.model.ViewRoutes

sealed class NavigationItems(
    val title: String,
    val icon: ImageVector,
    val description: String,
    val route: String
) {
    object scannerItem : NavigationItems(
        title = "Escaner",
        icon = Icons.Default.Home,
        description = "Escaner",
        route = ViewRoutes.scannerView.route
    )
    object listsItem : NavigationItems(
        title = "Listados",
        icon = Icons.Default.Info,
        description = "Listados",
        route = ViewRoutes.listsView.route
    )
    object reportsItem : NavigationItems(
        title = "Informes",
        icon = Icons.Default.Info,
        description = "Informes",
        route = ViewRoutes.reportsView.route
    )
    object formatsItem : NavigationItems(
        title = "Formatos",
        icon = Icons.Default.Info,
        description = "Formatos",
        route = ViewRoutes.formatsView.route
    )
    object contactsItem : NavigationItems(
        title = "Contactos",
        icon = Icons.Default.Info,
        description = "Contactos",
        route = ViewRoutes.contactsView.route
    )
}