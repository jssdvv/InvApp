package com.example.inv_app.ui.navigation.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.inv_app.R
import com.example.inv_app.data.model.ViewRoutes

sealed class NavigationItems(
    val title: String,
    val icon: Int,
    val description: String,
    val route: String
) {
    object homeItem : NavigationItems(
        title = "Inicio",
        icon = R.drawable.home_icon,
        description = "Inicio",
        route = ViewRoutes.homeView.route
    )
    object scannerItem : NavigationItems(
        title = "Escáner",
        icon = R.drawable.scanner_icon,
        description = "Escáner",
        route = ViewRoutes.scannerView.route
    )
    object listsItem : NavigationItems(
        title = "Listados",
        icon = R.drawable.lists_icon,
        description = "Listados",
        route = ViewRoutes.listsView.route
    )
    object reportsItem : NavigationItems(
        title = "Informes",
        icon = R.drawable.reports_icon,
        description = "Informes",
        route = ViewRoutes.reportsView.route
    )
    object formatsItem : NavigationItems(
        title = "Formatos",
        icon = R.drawable.formats_icon,
        description = "Formatos",
        route = ViewRoutes.formatsView.route
    )
    object contactsItem : NavigationItems(
        title = "Contactos",
        icon = R.drawable.contacts_icon,
        description = "Contactos",
        route = ViewRoutes.contactsView.route
    )
}