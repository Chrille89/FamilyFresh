package com.bach.familyfresh.features.actualmenu.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bach.familyfresh.features.actualmenu.bars.BottomAppBar
import com.bach.familyfresh.features.actualmenu.bars.TopAppBar
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenViewModel
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.ui.theme.FamilyFreshTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualMenuScreen(
    actualMenuScreenViewModel: ActualMenuScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar() },
        bottomBar = { BottomAppBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    MenuView(
                        "Gefüllte Paprikaschote",
                        "mit Kartoffeln und Soße",
                        listOf("Schwein", "Express"),
                        "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/E829412C-6272-4361-ADC1-DD016CBB03C1/Derivates/B0FA5272-D8BB-4E16-B56A-B9717A651432.jpg"
                    );
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    MenuView(
                        "Nudeln",
                        "mit Tomatensauce",
                        listOf("Vegetarisch"),
                        "https://static.bremer-gewuerzhandel.de/media/0a/83/48/1725976897/nudeln-mit-hackfleisch-00779-00789-00072-01304.webp"
                    );
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActualMenuScreenPreview() {
    FamilyFreshTheme {
        ActualMenuScreen()
    }
}