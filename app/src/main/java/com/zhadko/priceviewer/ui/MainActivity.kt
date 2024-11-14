package com.zhadko.priceviewer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zhadko.priceviewer.navigation.MainGraph
import com.zhadko.priceviewer.ui.screens.homeScreen.HomeScreen
import com.zhadko.priceviewer.ui.theme.PriceViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PriceViewerTheme {
                MainGraph()
            }
        }
    }
}