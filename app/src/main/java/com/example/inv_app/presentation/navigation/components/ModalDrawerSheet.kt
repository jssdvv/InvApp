package com.example.inv_app.presentation.navigation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

@Composable
fun ModalDrawerSheet(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavHostController
) {
    val items : List<NavigationItems> = listOf(
        NavigationItems.homeItem,
        NavigationItems.scannerItem,
        NavigationItems.listsItem,
        NavigationItems.reportsItem,
        NavigationItems.formatsItem,
        NavigationItems.contactsItem
    )
    ModalDrawerSheet(
        modifier = Modifier
            .width(200.dp)
    ) {
        Text(text = "Inventarios")
        Spacer(modifier = Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(item, drawerState, scope, navController)
        }
    }
}