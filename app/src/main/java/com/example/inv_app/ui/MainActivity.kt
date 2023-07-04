package com.example.inv_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.inv_app.ui.navigation.view.ModalNavigationDrawer
import com.example.inv_app.ui.navigation.view.Scaffold
import com.example.inv_app.ui.theme.InvAppTheme

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