package com.zhadko.priceviewer.data.dataSource.dto.price.instruments

data class Oanda(
    val defaultOrderSize: Int,
    val exchange: String,
    val symbol: String
)