package com.example.inv_app.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.inv_app.ui.viewmodel.CameraViewModel

@Composable
fun CameraScreen(
    onNavigateToCameraPreview: () -> Unit,

) {
    val viewModel = CameraViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla antes de camara"
        )
        Button(onClick = onNavigateToCameraPreview) {
            Text(text = "Iniciar camara")
        }
    }
}