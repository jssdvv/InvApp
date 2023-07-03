package com.example.inv_app.ui.navigation.design

import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationViewModel : ViewModel() {
    private val drawerState = MutableStateFlow(DrawerValue.Closed)

    fun openDrawer() {
        drawerState.value = DrawerValue.Open
    }

    fun closeDrawer() {
        drawerState.value = DrawerValue.Closed
    }
}