package com.example.inv_app.presentation.navigation.components

import com.example.inv_app.R
import com.example.inv_app.presentation.navigation.FeaturesRoutes

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
        route = FeaturesRoutes.home.route
    )
    object scannerItem : NavigationItems(
        title = "Escáner",
        icon = R.drawable.scanner_icon,
        description = "Escáner",
        route = FeaturesRoutes.scanner.route
    )
    object listsItem : NavigationItems(
        title = "Listados",
        icon = R.drawable.lists_icon,
        description = "Listados",
        route = FeaturesRoutes.lists.route
    )
    object reportsItem : NavigationItems(
        title = "Informes",
        icon = R.drawable.reports_icon,
        description = "Informes",
        route = FeaturesRoutes.reports.route
    )
    object formatsItem : NavigationItems(
        title = "Formatos",
        icon = R.drawable.formats_icon,
        description = "Formatos",
        route = FeaturesRoutes.formats.route
    )
    object contactsItem : NavigationItems(
        title = "Contactos",
        icon = R.drawable.contacts_icon,
        description = "Contactos",
        route = FeaturesRoutes.contacts.route
    )
}