package com.zhadko.priceviewer.data.dataSource.dto.price.history

import com.google.gson.annotations.SerializedName

data class PriceHistoryDto(
    @SerializedName("data")
    val historyData: List<Data>,
)