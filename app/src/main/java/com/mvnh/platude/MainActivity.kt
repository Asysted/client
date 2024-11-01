package com.mvnh.platude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mvnh.platude.ui.screen.MainScreen
import com.mvnh.platude.ui.theme.PlatudeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlatudeTheme {
                MainScreen()
            }
        }
    }
}