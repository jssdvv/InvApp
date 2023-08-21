package com.example.inv_app.presentation.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inv_app.R
import com.example.inv_app.ui.theme.Hum521BTFontFamily

@Composable
fun ModalDrawerSheet(
    navController: NavHostController,
    drawerState: DrawerState
) {
    val items: List<NavigationItems> = listOf(
        NavigationItems.homeItem,
        NavigationItems.scannerItem,
        NavigationItems.listsItem,
        NavigationItems.reportsItem,
        NavigationItems.formatsItem,
        NavigationItems.directoryItem
    )
    ModalDrawerSheet(
        modifier = Modifier.width(300.dp),
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        drawerShape = DrawerDefaults.shape,
        drawerContentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Text(
            text = stringResource(id = R.string.brand),
            style = TextStyle(
                fontFamily = Hum521BTFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.1.sp,
            ),
            modifier = Modifier.padding(start = 28.dp, top = 10.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface)
        )
        Spacer(modifier = Modifier.height(6.dp))
        items.forEach { item ->
            NavigationDrawerItem(navController, drawerState, item)
        }
    }
}