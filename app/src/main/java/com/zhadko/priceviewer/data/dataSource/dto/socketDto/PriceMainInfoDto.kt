package com.zhadko.priceviewer.data.dataSource.dto.socketDto

data class PriceMainInfoDto(
    val instrumentId: String,
    val last: Last?,
    val provider: String,
    val type: String
)