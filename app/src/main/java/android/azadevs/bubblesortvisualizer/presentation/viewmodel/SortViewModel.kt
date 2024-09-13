package android.azadevs.bubblesortvisualizer.presentation.viewmodel

import android.azadevs.bubblesortvisualizer.domain.usecase.SortUseCase
import android.azadevs.bubblesortvisualizer.presentation.SortDisplayItem
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Created by : Azamat Kalmurzayev
 * 13/09/24
 */
class SortViewModel(
    private val sortUseCase: SortUseCase = SortUseCase()
) : ViewModel() {

    var sortList = mutableStateListOf<SortDisplayItem>()

    var isEnd = mutableStateOf(true)

    init {
        refresh()
    }

    fun refresh() {
        sortList.clear()
        for (i in 0 until 9) {
            sortList.add(
                SortDisplayItem(
                    id = i, value = (0..100).random(), isCurrentlyCompared = false, color = Color(
                        red = (0..255).random(),
                        green = (0..150).random(),
                        blue = (0..100).random(),
                        alpha = 255
                    )
                )
            )
        }
    }

    fun startSorting() {
        viewModelScope.launch {
            isEnd.value = false
            sortUseCase(sortList.map { it.value }.toMutableList()).collect { sortData ->
                sortList[sortData.currentItemIndex] = sortList[sortData.currentItemIndex].copy(
                    isCurrentlyCompared = true
                )
                sortList[sortData.currentItemIndex + 1] =
                    sortList[sortData.currentItemIndex + 1].copy(
                        isCurrentlyCompared = true
                    )

                if (sortData.shouldSwap) {
                    val temp =
                        sortList[sortData.currentItemIndex].copy(isCurrentlyCompared = false)
                    sortList[sortData.currentItemIndex] =
                        sortList[sortData.currentItemIndex + 1].copy(isCurrentlyCompared = false)
                    sortList[sortData.currentItemIndex + 1] = temp
                }

                if (sortData.hadNoEffect) {
                    sortList[sortData.currentItemIndex] =
                        sortList[sortData.currentItemIndex].copy(
                            isCurrentlyCompared = false
                        )
                    sortList[sortData.currentItemIndex + 1] =
                        sortList[sortData.currentItemIndex + 1].copy(
                            isCurrentlyCompared = false
                        )
                }

                if (sortData.currentItemIndex == sortList.size - 2) {
                    isEnd.value = true
                }
            }
        }
    }

}