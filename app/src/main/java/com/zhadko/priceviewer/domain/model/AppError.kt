package com.zhadko.priceviewer.domain.model

data class AppError(
    val title: String = "Something went wrong",
) : Throwable()
