package com.zhadko.priceviewer.ui.screens.authScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhadko.priceviewer.ui.components.LoadingComponent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    navigateHome: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        LoadingComponent()
    }

    LaunchedEffect("") {
        viewModel.singleEvent.collectLatest { event ->
            when (event) {
                AuthSingleEvent.NavigateHome -> navigateHome()
            }
        }
    }
}