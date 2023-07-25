package com.example.inv_app.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inv_app.presentation.screens.ContactsView
import com.example.inv_app.presentation.screens.FormatsView
import com.example.inv_app.presentation.screens.home_screen.HomeView
import com.example.inv_app.presentation.screens.lists_screen.ListsView
import com.example.inv_app.presentation.screens.reports_screen.ReportsView
import com.example.inv_app.presentation.screens.scanner_screen.ScannerScreen

internal sealed class ScreenRoutes(val route: String) {
    object homeScreen : ScreenRoutes("homeScreen")
    object scannerScreen : ScreenRoutes("scannerScreen")
    object listsScreen : ScreenRoutes("listsScreen")
    object reportsScreen : ScreenRoutes("reportsScreen")
    object formatsScreen : ScreenRoutes("formatsScreen")
    object contactsScreen : ScreenRoutes("contactsScreen")
}

@Composable
fun AppNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = ScreenRoutes.homeScreen.route
    ) {
        composable(ScreenRoutes.homeScreen.route) { HomeView() }
        composable(ScreenRoutes.scannerScreen.route) { ScannerScreen() }
        composable(ScreenRoutes.listsScreen.route) { ListsView() }
        composable(ScreenRoutes.reportsScreen.route) { ReportsView() }
        composable(ScreenRoutes.formatsScreen.route) { FormatsView() }
        composable(ScreenRoutes.contactsScreen.route) { ContactsView() }
    }
}