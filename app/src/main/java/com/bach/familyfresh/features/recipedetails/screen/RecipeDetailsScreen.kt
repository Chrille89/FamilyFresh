package com.bach.familyfresh.features.recipedetails.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.features.recipedetails.viewmodel.RecipeDetailsScreenViewModel
import com.bach.familyfresh.ui.theme.FamilyFreshTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(
    recipeDetailsScreenViewModel : RecipeDetailsScreenViewModel = viewModel(),
    onClickBack: () -> Unit
) {
    var recipe = recipeDetailsScreenViewModel.recipeState.value;
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Zubereitung")
                },
                navigationIcon = {
                    IconButton(onClick = {onClickBack()}) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    MenuView(
                        recipe.title,
                        subTitle = recipe.subtitle ?: "",
                        labels = recipe.labels?.map { recipe -> recipe.value } ?: emptyList(),
                        recipe.duration.toString()
                    )
                    AsyncImage(
                        model = recipe.image,
                        contentDescription = null,
                        Modifier.fillMaxWidth()
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            recipe.nutrients?.forEach { nutrient ->
                                Column(Modifier.weight(1f)) {
                                    Text(
                                        nutrient.name ?: "",
                                        Modifier.fillMaxWidth(),
                                        style = MaterialTheme.typography.titleMedium,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        nutrient.amount.toString()+nutrient.unit?.value,
                                        Modifier.fillMaxWidth(),
                                        style = MaterialTheme.typography.titleSmall,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }
                    }
                }
            }

            item {
                Card(Modifier.padding(10.dp)) {
                    Column(
                        Modifier.padding(5.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        recipe.preparation?.forEachIndexed { index, preparation  ->
                            Text((index+1).toString() +". "+ preparation)
                        }
                    }
                }
            }
        }
    }
}
