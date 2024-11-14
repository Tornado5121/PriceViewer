package com.zhadko.priceviewer.ui.screens.homeScreen

import androidx.lifecycle.viewModelScope
import com.zhadko.priceviewer.base.BaseViewModel
import com.zhadko.priceviewer.domain.model.InstrumentModel
import com.zhadko.priceviewer.domain.repository.AuthRepository
import com.zhadko.priceviewer.domain.repository.FinancialRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val financialRepository: FinancialRepository,
    private val authRepository: AuthRepository,
) : BaseViewModel<HomeState, HomeSingleEvent>(
    initialState = HomeState(
        isLoading = true,
        mainData = null,
        chartHistoryData = null,
        instrumentsList = null,
        error = null
    )
) {

    init {
        login()
        updateInstrumentsList()
    }

    private fun login() {
        viewModelScope.launch {
            authRepository.login("r_test@fintatech.com", "kisfiz-vUnvy9-sopnyv")
        }
    }

    fun displayChosenPriceInfo(instrument: InstrumentModel) {
        viewModelScope.launch {
            subscribePriceMainData(instrument.symbol)
            collectNewPriceData(instrument.id)
            val token = authRepository.getToken()
            financialRepository.startListenToPrices(token, instrument.id)
        }
    }

    private fun subscribePriceMainData(instrumentSymbol: String) {
        viewModelScope.launch {
            financialRepository.priceMainInfo.collectLatest {
                updateState(
                    HomeState(
                        isLoading = false,
                        mainData = it.copy(symbol = instrumentSymbol),
                        chartHistoryData = state.value.chartHistoryData,
                        instrumentsList = state.value.instrumentsList
                    )
                )
            }
        }
    }

    private fun collectNewPriceData(instrumentId: String) {
        viewModelScope.launch {
            val historyPrices = financialRepository.getPriceHistoryList(instrumentId)
            updateState(
                HomeState(
                    isLoading = false,
                    mainData = state.value.mainData,
                    chartHistoryData = historyPrices,
                    instrumentsList = state.value.instrumentsList,
                    error = null
                )
            )
        }
    }

    private fun updateInstrumentsList() {
        viewModelScope.launch {
            val instrumentList = financialRepository.getInstrumentsList()
            updateState(
                HomeState(
                    isLoading = false,
                    mainData = state.value.mainData,
                    chartHistoryData = state.value.chartHistoryData,
                    instrumentsList = instrumentList
                )
            )
        }
    }
}