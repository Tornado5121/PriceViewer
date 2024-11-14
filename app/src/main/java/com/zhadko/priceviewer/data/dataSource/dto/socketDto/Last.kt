package com.zhadko.priceviewer.data.dataSource.dto.socketDto

data class Last(
    val change: Double?,
    val changePct: Double?,
    val price: Double?,
    val timestamp: String?,
    val volume: Int?
)