package com.example.inv_app.presentation.navigation

import com.example.inv_app.R

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
        route = ScreenRoutes.homeScreen.route
    )
    object scannerItem : NavigationItems(
        title = "Escáner",
        icon = R.drawable.scanner_icon,
        description = "Escáner",
        route = ScreenRoutes.scannerScreen.route
    )
    object listsItem : NavigationItems(
        title = "Listados",
        icon = R.drawable.lists_icon,
        description = "Listados",
        route = ScreenRoutes.listsScreen.route
    )
    object reportsItem : NavigationItems(
        title = "Informes",
        icon = R.drawable.reports_icon,
        description = "Informes",
        route = ScreenRoutes.reportsScreen.route
    )
    object formatsItem : NavigationItems(
        title = "Formatos",
        icon = R.drawable.formats_icon,
        description = "Formatos",
        route = ScreenRoutes.formatsScreen.route
    )
    object contactsItem : NavigationItems(
        title = "Contactos",
        icon = R.drawable.contacts_icon,
        description = "Contactos",
        route = ScreenRoutes.contactsScreen.route
    )
}