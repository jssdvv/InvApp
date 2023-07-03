package com.example.inv_app.ui.navigation.design

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModalDrawerSheet() {
    val items : List<NavigationItems> = listOf(
        NavigationItems.homeItem,
        NavigationItems.cameraItem
    )
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(item)
        }
    }
}