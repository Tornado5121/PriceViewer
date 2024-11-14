package com.zhadko.priceviewer.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.zhadko.priceviewer.domain.mappers.date.toProperTime
import com.zhadko.priceviewer.ui.screens.homeScreen.HomeState

@Composable
fun MarketData(modifier: Modifier = Modifier, data: HomeState) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Market Data")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RectangleShape)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextWithPlaceHolder(placeholder = "Symbol:", value = data.mainData?.symbol ?: "N/A")
            TextWithPlaceHolder(placeholder = "Price:", value = data.mainData?.price.toString())
            TextWithPlaceHolder(placeholder = "Time:", value = data.mainData?.time?.toProperTime().toString())
        }
    }
}