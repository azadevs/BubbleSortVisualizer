package android.azadevs.bubblesortvisualizer.presentation

import androidx.compose.ui.graphics.Color

/**
 * Created by : Azamat Kalmurzayev
 * 13/09/24
 */
data class SortDisplayItem(
    val id: Int,
    val value: Int,
    val isCurrentlyCompared: Boolean,
    val color: Color,
)
