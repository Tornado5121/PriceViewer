package com.zhadko.priceviewer.data.repositories

import com.zhadko.priceviewer.data.dataSource.network.PriceApi
import com.zhadko.priceviewer.data.dataSource.socket.SocketConnection
import com.zhadko.priceviewer.domain.mappers.price.toDomain
import com.zhadko.priceviewer.domain.mappers.toPoint
import com.zhadko.priceviewer.domain.model.InstrumentModel
import com.zhadko.priceviewer.domain.model.PriceHistoryChartModel
import com.zhadko.priceviewer.domain.model.PriceMainInfoModel
import com.zhadko.priceviewer.domain.repository.FinancialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FinancialRepositoryImpl @Inject constructor(
    private val socketFetcher: SocketConnection,
    private val priceApi: PriceApi,
) : FinancialRepository {

    override val priceMainInfo: Flow<PriceMainInfoModel>
        get() = socketFetcher.priceMainData

    override suspend fun getPriceHistoryList(instrumentId: String): PriceHistoryChartModel {
        val result = withContext(Dispatchers.IO) {
            priceApi.getPriceHistoryList(
                instrumentId = instrumentId,
                provider = "oanda",
                interval = 10,
                periodicity = "hour",
                barsCount = 10
            )
        }
        return PriceHistoryChartModel(result.historyData.toPoint())
    }

    override suspend fun startListenToPrices(token: String, instrumentId: String) {
        socketFetcher.closeWebSocket()
        socketFetcher.connectSocket(token, instrumentId)
    }

    override suspend fun getInstrumentsList(): List<InstrumentModel> {
        return withContext(Dispatchers.IO) {
            priceApi.getInstrumentsList(
                provider = "oanda",
                kind = "forex",
                symbol = null,
                page = 1,
                size = 10
            ).toDomain()
        }
    }
}