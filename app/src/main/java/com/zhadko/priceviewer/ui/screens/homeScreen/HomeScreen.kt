package com.zhadko.priceviewer.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhadko.priceviewer.ui.components.ChooseSymbolSubscription
import com.zhadko.priceviewer.ui.components.LoadingComponent
import com.zhadko.priceviewer.ui.components.MarketData
import com.zhadko.priceviewer.ui.components.PriceHistoryChart

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        LoadingComponent()
    } else {
        Column(
            modifier = modifier.padding(10.dp)
        ) {
            ChooseSymbolSubscription(
                optionsList = state.instrumentsList ?: emptyList(),
                subscribePrice = { chosenInstrument ->
                    viewModel.displayChosenPriceInfo(chosenInstrument)
                })
            MarketData(modifier = Modifier.padding(top = 20.dp), data = state)

            if (state.chartHistoryData != null) {
                val result = state.chartHistoryData
                PriceHistoryChart(
                    modifier = Modifier.padding(top = 10.dp), data = result!!
                )
            }
        }
    }
}