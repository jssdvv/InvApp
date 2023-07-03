package com.example.inv_app.ui.navigation.design

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import  androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun NavigationDrawerItem(item: NavigationItems){
    NavigationDrawerItem(
        label = { Text(text = item.title)},
        selected = false,
        onClick = {},
        modifier = Modifier,
        icon = { Icon(imageVector = item.icon, contentDescription = item.iconDescription) },
        badge = null
    )
}