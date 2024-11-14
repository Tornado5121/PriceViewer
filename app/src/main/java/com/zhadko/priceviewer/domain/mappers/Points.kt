package com.zhadko.priceviewer.domain.mappers

import com.zhadko.priceviewer.data.dataSource.dto.price.history.Data
import com.zhadko.priceviewer.domain.mappers.price.HistoryPriceModel

fun Data.toPoint(): HistoryPriceModel {
    return HistoryPriceModel(
        time = this.t,
        value = (this.h * 100).toFloat()
    )
}

fun List<Data>.toPoint() = this.map { it.toPoint() }
