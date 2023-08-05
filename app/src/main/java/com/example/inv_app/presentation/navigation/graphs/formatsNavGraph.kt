package com.example.inv_app.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.inv_app.presentation.features.FormatsScreen
import com.example.inv_app.presentation.navigation.FeaturesRoutes

internal sealed class FormatsRoutes(val route: String) {
    object formats : FormatsRoutes("formatsScreen")
}

fun NavGraphBuilder.formatsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = FormatsRoutes.formats.route,
        route = FeaturesRoutes.formats.route
    ) {
        composable(FormatsRoutes.formats.route) { FormatsScreen() }
    }
}