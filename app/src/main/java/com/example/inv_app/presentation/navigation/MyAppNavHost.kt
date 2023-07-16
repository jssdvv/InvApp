package com.example.inv_app.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inv_app.data.model.ScreenViewRoutes
import com.example.inv_app.presentation.screens.ContactsView
import com.example.inv_app.presentation.screens.FormatsView
import com.example.inv_app.presentation.screens.home_screen.HomeView
import com.example.inv_app.presentation.screens.lists_screen.ListsView
import com.example.inv_app.presentation.screens.reports_screen.ReportsView
import com.example.inv_app.presentation.screens.scanner_screen.ScannerView

@Composable
fun MyAppNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = ScreenViewRoutes.homeScreenView.route
    ) {
        composable(ScreenViewRoutes.homeScreenView.route) { HomeView() }
        composable(ScreenViewRoutes.scannerScreenView.route) { ScannerView() }
        composable(ScreenViewRoutes.listsScreenView.route) { ListsView() }
        composable(ScreenViewRoutes.reportsScreenView.route) { ReportsView() }
        composable(ScreenViewRoutes.formatsScreenView.route) { FormatsView() }
        composable(ScreenViewRoutes.contactsScreenView.route) { ContactsView() }
    }
}