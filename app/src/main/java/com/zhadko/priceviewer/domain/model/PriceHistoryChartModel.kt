package com.zhadko.priceviewer.domain.model

import com.zhadko.priceviewer.domain.mappers.price.HistoryPriceModel

data class PriceHistoryChartModel(

    val priceHistoryList: List<HistoryPriceModel>,
)
