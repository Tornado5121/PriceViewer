package com.zhadko.priceviewer.data.dataSource.socket

import com.zhadko.priceviewer.domain.model.PriceMainInfoModel
import kotlinx.coroutines.flow.SharedFlow

interface SocketConnection {

    val priceMainData: SharedFlow<PriceMainInfoModel>

    suspend fun connectSocket(token: String, currencyId: String)
    fun closeWebSocket()
}