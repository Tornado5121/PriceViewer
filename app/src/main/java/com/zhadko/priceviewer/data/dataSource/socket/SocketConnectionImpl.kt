package com.zhadko.priceviewer.data.dataSource.socket

import com.google.gson.Gson
import com.zhadko.priceviewer.data.dataSource.dto.socketDto.PriceMainInfoDto
import com.zhadko.priceviewer.domain.mappers.price.toDomain
import com.zhadko.priceviewer.domain.model.PriceMainInfoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

class SocketConnectionImpl @Inject constructor(
    private val coroutineScope: CoroutineScope,
) : SocketConnection {

    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    private val _priceMainData = MutableSharedFlow<PriceMainInfoModel>()
    override val priceMainData: SharedFlow<PriceMainInfoModel> = _priceMainData.asSharedFlow()

    override suspend fun connectSocket(token: String, currencyId: String) {
        val request = Request.Builder()
            .url("wss://platform.fintacharts.com/api/streaming/ws/v1/realtime?token=$token")
            .build()

        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("Connected to WebSocket")
                // Send initial message if needed
                val subscriptionMessage = """
    {
        "type": "l1-subscription",
        "id": "1",
        "instrumentId": "$currencyId",
        "provider": "simulation",
        "subscribe": true,
        "kinds": ["last"]
    }
"""
                webSocket.send(subscriptionMessage)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                coroutineScope.launch {
                    val priceMainInfoDTO = Gson().fromJson(text, PriceMainInfoDto::class.java)
                    val priceMainInfoModel = priceMainInfoDTO.toDomain()
                    _priceMainData.emit(priceMainInfoModel)
                    println("Received message: $priceMainInfoModel")
                }
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                println("Received bytes: $bytes")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                println("WebSocket Closing: $code / $reason")
                webSocket.close(1000, null)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("WebSocket Error: ${t.message}")
            }
        }

        webSocket = client.newWebSocket(request, listener)
    }

    override fun closeWebSocket() {
        if (webSocket != null) {
            webSocket?.close(1000, "Closing WebSocket connection")
            webSocket = null
        }
    }
}
