package com.example.inv_app.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.inv_app.presentation.navigation.graphs.*
import kotlinx.coroutines.flow.MutableStateFlow

sealed class FeaturesRoutes(val route: String) {
    object home : FeaturesRoutes("homeFeature")
    object scanner : FeaturesRoutes("scannerFeature")
    object lists : FeaturesRoutes("listsFeature")
    object reports : FeaturesRoutes("reportsFeature")
    object formats : FeaturesRoutes("formatsFeature")
    object directory : FeaturesRoutes("directoryFeature")
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    drawerState: DrawerState,
    isGesturesEnabled: MutableStateFlow<Boolean>
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = FeaturesRoutes.home.route,
        route = null
    ) {
        homeNavGraph(navController)
        searchNavGraph(navController, drawerState, isGesturesEnabled)
        listsNavGraph(navController)
        reportsNavGraph(navController)
        formatsNavGraph(navController)
        directoryNavGraph(navController)
    }
}