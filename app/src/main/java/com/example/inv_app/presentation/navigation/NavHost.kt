package com.example.inv_app.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.inv_app.presentation.navigation.graphs.*

sealed class FeaturesRoutes(val route: String) {
    object home : FeaturesRoutes("homeFeature")
    object scanner : FeaturesRoutes("scannerFeature")
    object lists : FeaturesRoutes("listsFeature")
    object reports : FeaturesRoutes("reportsFeature")
    object formats : FeaturesRoutes("formatsFeature")
    object directory : FeaturesRoutes("directoryFeature")
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
        homeNavGraph(navController)
        scannerNavGraph(navController)
        listsNavGraph(navController)
        reportsNavGraph(navController)
        formatsNavGraph(navController)
        directoryNavGraph(navController)
    }
}