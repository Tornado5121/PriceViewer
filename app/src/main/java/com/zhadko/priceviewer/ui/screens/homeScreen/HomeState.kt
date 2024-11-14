package com.zhadko.priceviewer.ui.screens.homeScreen

import com.zhadko.priceviewer.domain.model.AppError
import com.zhadko.priceviewer.domain.model.InstrumentModel
import com.zhadko.priceviewer.domain.model.PriceHistoryChartModel
import com.zhadko.priceviewer.domain.model.PriceMainInfoModel

data class HomeState(
    val isLoading: Boolean,
    val mainData: PriceMainInfoModel?,
    val chartHistoryData: PriceHistoryChartModel?,
    val instrumentsList: List<InstrumentModel>?,
    val error: AppError? = null,
)
