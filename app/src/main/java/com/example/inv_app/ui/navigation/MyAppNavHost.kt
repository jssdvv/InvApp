package com.example.inv_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inv_app.data.model.ViewRoutes
import com.example.inv_app.ui.view.*

@Composable
fun MyAppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ViewRoutes.homeView.route
    ) {
        composable(ViewRoutes.homeView.route) {
            HomeView(
                onNavigateToCamera = { navController.navigate(ViewRoutes.listsView.route) }
            )
        }
        composable(ViewRoutes.scannerView.route) { ScannerView() }
        composable(ViewRoutes.listsView.route) { ListsView() }
        composable(ViewRoutes.reportsView.route) { ReportsView() }
        composable(ViewRoutes.formatsView.route) { FormatsView() }
        composable(ViewRoutes.contactsView.route) { ContactsView() }
    }
}