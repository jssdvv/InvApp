package com.example.inv_app.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inv_app.presentation.features.lists.ListsScreen
import com.example.inv_app.presentation.navigation.FeaturesRoutes

internal sealed class ListsRoutes(val route: String) {
    object lists : ListsRoutes("listsScreen")
}

fun NavGraphBuilder.listsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ListsRoutes.lists.route,
        route = FeaturesRoutes.lists.route
    ) {
        composable(ListsRoutes.lists.route) { ListsScreen() }
    }
}