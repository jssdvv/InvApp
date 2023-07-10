package com.example.inv_app.ui.navigation

import android.view.Window
import android.view.WindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inv_app.data.model.ViewRoutes
import com.example.inv_app.ui.view.*

@Composable
fun MyAppNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = ViewRoutes.homeView.route
    ) {
        composable(ViewRoutes.homeView.route) {
            HomeView(
                onNavigateToCamera = { navController.navigate(ViewRoutes.scannerView.route) }
            )
        }
        composable(ViewRoutes.scannerView.route) { ScannerView() }
        composable(ViewRoutes.listsView.route) { ListsView() }
        composable(ViewRoutes.reportsView.route) { ReportsView() }
        composable(ViewRoutes.formatsView.route) { FormatsView() }
        composable(ViewRoutes.contactsView.route) { ContactsView() }
    }
}