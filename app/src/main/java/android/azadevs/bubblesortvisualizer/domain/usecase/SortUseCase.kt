package android.azadevs.bubblesortvisualizer.domain.usecase

import android.azadevs.bubblesortvisualizer.domain.model.SortData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by : Azamat Kalmurzayev
 * 13/09/24
 */
class SortUseCase {

    operator fun invoke(list: MutableList<Int>): Flow<SortData> = flow {

        var listSize = list.size - 1

        while (listSize > 1) {

            var index = 0

            while (index < listSize) {
                emit(SortData(currentItemIndex = index, shouldSwap = false, hadNoEffect = false))
                delay(300)
                if (list[index] > list[index + 1]) {
                    list.swap(index, index + 1)
                    emit(SortData(currentItemIndex = index, shouldSwap = true, hadNoEffect = false))
                } else {
                    emit(SortData(currentItemIndex = index, shouldSwap = false, hadNoEffect = true))
                }
                delay(300)
                index++
            }

            listSize--
        }
    }

    private fun MutableList<Int>.swap(a: Int, b: Int) {
        val tmp = this[a]
        this[a] = this[b]
        this[b] = tmp
    }

}