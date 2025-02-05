package com.bach.familyfresh.features.actualmenu.screen

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bach.familyfresh.R
import com.bach.familyfresh.features.actualmenu.bars.BottomAppBar
import com.bach.familyfresh.features.actualmenu.bars.TopAppBar
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenViewModel
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.ui.theme.FamilyFreshTheme
import dev.ricknout.composesensors.accelerometer.rememberAccelerometerSensorValueAsState
import org.openapitools.client.models.RecipeReadDto

@Composable
fun ActualMenuScreen(
    actualMenuScreenViewModel: ActualMenuScreenViewModel = viewModel(),
    onRecipeClick: (recipe: RecipeReadDto) -> Unit,
    modifier: Modifier = Modifier
) {
    val mMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.dice_sound);
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar {
            mMediaPlayer.start()
            actualMenuScreenViewModel.getActualMenu(true)
        }  },
        bottomBar = {
            BottomAppBar()
        }
    ) { innerPadding ->
        // Remember accelerometer sensor value as State that updates as SensorEvents arrive
        val sensorValue by rememberAccelerometerSensorValueAsState()
        // Accelerometer sensor values. Also available: sensorValue.timestamp, sensorValue.accuracy
        val (x,y,z) = sensorValue.value

        if(x > 20 || y > 20 || z > 20) {
            mMediaPlayer.start()
            actualMenuScreenViewModel.getActualMenu(true)
        };

        when (val uiState = actualMenuScreenViewModel.menus.value) {
            is ActualMenuScreenStatus.loading ->
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }

            is ActualMenuScreenStatus.error -> Text("Error")
            is ActualMenuScreenStatus.success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(uiState.menus) { recipe ->
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 10.dp),
                            onClick = { onRecipeClick(recipe) }
                        ) {
                            MenuView(
                                recipe.title,
                                subTitle = recipe.subtitle ?: "",
                                labels = recipe.labels?.map { label -> label.name } ?: emptyList(),
                                recipe.duration.toString()
                            )
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = recipe.image,
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

