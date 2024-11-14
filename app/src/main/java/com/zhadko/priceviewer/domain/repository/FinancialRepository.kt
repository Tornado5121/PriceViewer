package com.zhadko.priceviewer.domain.repository

import com.zhadko.priceviewer.domain.model.InstrumentModel
import com.zhadko.priceviewer.domain.model.PriceHistoryChartModel
import com.zhadko.priceviewer.domain.model.PriceMainInfoModel
import kotlinx.coroutines.flow.Flow

interface FinancialRepository {

    val priceMainInfo: Flow<PriceMainInfoModel>

    suspend fun getPriceHistoryList(instrumentId:String): PriceHistoryChartModel
    suspend fun startListenToPrices(token: String, instrumentId: String)
    suspend fun getInstrumentsList(): List<InstrumentModel>
}