package android.azadevs.bubblesortvisualizer.domain.model

/**
 * Created by : Azamat Kalmurzayev
 * 13/09/24
 */
data class SortData(
    val currentItemIndex: Int,
    val shouldSwap: Boolean,
    val hadNoEffect: Boolean
)
