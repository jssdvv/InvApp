package com.example.inv_app.presentation.navigation.components

import com.example.inv_app.R
import com.example.inv_app.presentation.navigation.FeaturesRoutes

sealed class NavigationItems(
    val title: Int,
    val icon: Int,
    val iconDescription: String,
    val route: String
) {
    object homeItem : NavigationItems(
        title = R.string.home,
        icon = R.drawable.home_icon,
        iconDescription = "Icon of homeNavGraph item destination",
        route = FeaturesRoutes.home.route
    )
    object scannerItem : NavigationItems(
        title = R.string.scanner,
        icon = R.drawable.scanner_icon,
        iconDescription = "Icon of scannerNavGraph item destination",
        route = FeaturesRoutes.scanner.route
    )
    object listsItem : NavigationItems(
        title = R.string.lists,
        icon = R.drawable.lists_icon,
        iconDescription = "Icon of listsNavGraph item destination",
        route = FeaturesRoutes.lists.route
    )
    object reportsItem : NavigationItems(
        title = R.string.reports,
        icon = R.drawable.reports_icon,
        iconDescription = "Icon of reportsNavGraph item destination",
        route = FeaturesRoutes.reports.route
    )
    object formatsItem : NavigationItems(
        title = R.string.formats,
        icon = R.drawable.formats_icon,
        iconDescription = "Icon of formatsNavGraph item destination",
        route = FeaturesRoutes.formats.route
    )
    object directoryItem : NavigationItems(
        title = R.string.directory,
        icon = R.drawable.contacts_icon,
        iconDescription = "Icon of directoryNavGraph item destination",
        route = FeaturesRoutes.directory.route
    )
}