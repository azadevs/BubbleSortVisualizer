package android.azadevs.bubblesortvisualizer

import android.azadevs.bubblesortvisualizer.presentation.viewmodel.SortViewModel
import android.azadevs.bubblesortvisualizer.ui.theme.BubbleSortVisualizerTheme
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val viewModel = SortViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleSortVisualizerTheme {
                SortScreen(viewModel = viewModel, this)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortScreen(viewModel: SortViewModel, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (viewModel.isEnd.value) {
                    viewModel.startSorting()
                } else {
                    Toast.makeText(context, "Sorting is running", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(
                    text = "Start Sorting",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Button(onClick = {
                if (viewModel.isEnd.value) {
                    viewModel.refresh()
                } else {
                    Toast.makeText(context, "Sorting is running", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(
                    text = "Refresh List",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                viewModel.sortList,
                key = { it.id }
            ) {
                val borderStroke = if (it.isCurrentlyCompared) {
                    BorderStroke(3.dp, Color.White)
                } else {
                    BorderStroke(0.dp, Color.Transparent)
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = it.color, RoundedCornerShape(12.dp))
                        .border(borderStroke, shape = RoundedCornerShape(12.dp))
                        .animateItemPlacement(
                            tween(300)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.value.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}


