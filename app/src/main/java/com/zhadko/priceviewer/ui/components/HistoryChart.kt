package com.zhadko.priceviewer.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.zhadko.priceviewer.domain.model.PriceHistoryChartModel

@Composable
fun PriceHistoryChart(modifier: Modifier = Modifier, data: PriceHistoryChartModel) {
    // Find the min and max values in the data
    val minValue = data.priceHistoryList.minOfOrNull { it.value } ?: 0f
    val maxValue = data.priceHistoryList.maxOfOrNull { it.value } ?: 100f

    val

    // Define the number of steps for the Y-axis
    val steps = 5

    // Calculate the range for Y-axis scaling
    val range = maxValue - minValue
    val yStepSize = range / steps

    // X-axis data setup
    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(data.priceHistoryList.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    // Y-axis data setup with dynamic range labels
    val yAxisData = AxisData.Builder()
        .backgroundColor(Color.Transparent)
        .steps(steps)
        .labelData { i ->
            val yValue = minValue + i * yStepSize
            String.format("%.2f", yValue) // Formatting to two decimal places
        }
        .labelAndAxisLinePadding(20.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    // Line chart data setup
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = data.priceHistoryList,
                    LineStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(color = MaterialTheme.colorScheme.tertiary),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.inversePrimary,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
    )

    // Chart container
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(30.dp)),
    ) {
        LineChart(
            modifier = modifier.fillMaxSize(),
            lineChartData = lineChartData
        )
    }
}
