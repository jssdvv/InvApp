package com.example.inv_app.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inv_app.presentation.features.ContactsView
import com.example.inv_app.presentation.features.FormatsView
import com.example.inv_app.presentation.features.home.HomeView
import com.example.inv_app.presentation.features.lists.ListsScreen
import com.example.inv_app.presentation.features.reports.ReportsView
import com.example.inv_app.presentation.navigation.graphs.scannerNavGraph

sealed class FeaturesRoutes(val route: String) {
    object home : FeaturesRoutes("homeFeature")
    object scanner : FeaturesRoutes("scannerFeature")
    object lists : FeaturesRoutes("listsFeature")
    object reports : FeaturesRoutes("reportsFeature")
    object formats : FeaturesRoutes("formatsFeature")
    object contacts : FeaturesRoutes("contactsFeature")
}

@Composable
fun NavHost(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = FeaturesRoutes.home.route
    ) {
        scannerNavGraph(navController)
        composable(FeaturesRoutes.home.route) { HomeView() }
        composable(FeaturesRoutes.lists.route) { ListsScreen() }
        composable(FeaturesRoutes.reports.route) { ReportsView() }
        composable(FeaturesRoutes.formats.route) { FormatsView() }
        composable(FeaturesRoutes.contacts.route) { ContactsView() }
    }
}