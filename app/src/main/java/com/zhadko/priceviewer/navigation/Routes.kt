package com.zhadko.priceviewer.navigation

sealed class Routes(val value: String) {
    object Auth : Routes("auth_screen")
    object Home : Routes("home_screen")
}