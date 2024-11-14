package com.zhadko.priceviewer.data.dataSource.dto.price.history

data class SubscribePriceRequestDto(
    val id: String,
    val instrumentId: String,
    val kinds: List<String>,
    val provider: String,
    val subscribe: Boolean,
    val type: String
)