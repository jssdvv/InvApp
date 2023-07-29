package com.example.inv_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.inv_app.presentation.navigation.components.ModalNavigationDrawer
import com.example.inv_app.ui.theme.InvAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InvAppTheme {
                Surface {
                    ModalNavigationDrawer()
                }
            }
        }
    }
}