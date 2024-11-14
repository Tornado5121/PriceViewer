package com.zhadko.priceviewer.data.dataSource.dto.price.instruments

data class Data(
    val baseCurrency: String,
    val currency: String,
    val description: String,
    val id: String,
    val kind: String,
    val mappings: Mappings,
    val profile: Profile,
    val symbol: String,
    val tickSize: Double
)