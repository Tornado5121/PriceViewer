package com.zhadko.priceviewer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhadko.priceviewer.ui.screens.authScreen.AuthScreen
import com.zhadko.priceviewer.ui.screens.homeScreen.HomeScreen

@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.Auth.value) {
        composable(route = Routes.Auth.value) {
            AuthScreen {
                navController.navigate(Routes.Home.value)
            }
        }
        composable(route = Routes.Home.value) { HomeScreen() }
    }
}