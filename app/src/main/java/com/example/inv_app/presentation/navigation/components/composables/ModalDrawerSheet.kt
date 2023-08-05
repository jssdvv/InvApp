package com.example.inv_app.presentation.navigation.components.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.inv_app.presentation.navigation.components.NavigationItems
import com.example.inv_app.ui.theme.Hum521BTFontFamily

@Composable
fun ModalDrawerSheet(
    drawerState: DrawerState,
    navController: NavHostController
) {
    val items : List<NavigationItems> = listOf(
        NavigationItems.homeItem,
        NavigationItems.scannerItem,
        NavigationItems.listsItem,
        NavigationItems.reportsItem,
        NavigationItems.formatsItem,
        NavigationItems.directoryItem
    )
    ModalDrawerSheet(
        modifier = Modifier
            .width(300.dp)
            ,
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
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(item, drawerState, navController)
        }
    }
}