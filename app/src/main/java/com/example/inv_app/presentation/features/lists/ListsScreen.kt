package com.example.inv_app.presentation.features.lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.inv_app.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListsScreen() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { 2 }
    )
    val coroutineScope = rememberCoroutineScope()

    val inactiveColor = Color(0xFF777777)

    val tabLabels = listOf(
        TabLabel(
            text = "Escanear",
            icon = painterResource(id = R.drawable.scanner_icon),
            description = "Tab para escanear"
        ),
        TabLabel(
            text = "Historial",
            icon = painterResource(id = R.drawable.lists_icon),
            description = "Tab para ver el historial"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            contentColor = Color.Black,
            containerColor = Color.Transparent
        ) {
            tabLabels.forEachIndexed { index, tabLabel ->
                Tab(
                    modifier = Modifier.fillMaxWidth(),
                    selected = pagerState.currentPage == index,
                    selectedContentColor = Color.Black,
                    unselectedContentColor = inactiveColor,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = tabLabel.icon,
                            contentDescription = tabLabel.description,
                            tint = if (pagerState.currentPage == index) Color.Black else inactiveColor
                        )
                        Text(text = tabLabel.text)
                    }
                }
            }
        }

        HorizontalPager(
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                ) { Text(text = "hola1") }
                1 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                ) { Text(text = "hola2") }
            }
        }


    }
}

data class TabLabel(
    val text: String,
    val icon: Painter,
    val description: String
)