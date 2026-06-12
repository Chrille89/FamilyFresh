package com.bach.familyfresh.features.actualmenu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bach.familyfresh.R
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenViewModel
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.features.actualmenu.views.TabBarItem
import com.bach.familyfresh.features.actualmenu.views.TabView
import com.bach.familyfresh.network.models.RecipeReadDto
import com.bach.familyfresh.ui.views.ImageView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualMenuScreen(
    modifier: Modifier = Modifier,
    actualMenuScreenViewModel: ActualMenuScreenViewModel = viewModel(),
    onRecipeClick: (recipe: RecipeReadDto) -> Unit,
    onClickTab: (title: String, menu: List<RecipeReadDto>) -> Unit,
    selectedTabIndex: Int,
) {
    val horizontalPadding = 10.dp;
    val verticalPadding = 10.dp;
    val aiRecipeInput = remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title =
            {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.title_actual_menu), style = MaterialTheme.typography.titleLarge)
                }
            })
        },
        bottomBar = {
            val menuTab = TabBarItem(
                title = stringResource(R.string.tab_menus),
                selectedIcon = ImageVector.vectorResource(R.drawable.restaurant_24px),
                unselectedIcon = ImageVector.vectorResource(R.drawable.restaurant_24px)
            )
            val changeMenuTab =  TabBarItem(
                title = stringResource(R.string.tab_change_meals),
                selectedIcon = ImageVector.vectorResource(R.drawable.change_circle_24px),
                unselectedIcon = ImageVector.vectorResource(R.drawable.change_circle_24px)
            )
            TabView(listOf(menuTab, changeMenuTab),selectedTabIndex) { tabTitle ->
                onClickTab(tabTitle,(actualMenuScreenViewModel.menus.value as ActualMenuScreenStatus.success).menus)
            }
        }
    ) { innerPadding ->
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
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontalPadding, verticalPadding),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            TextField(
                                value = aiRecipeInput.value,
                                onValueChange = { aiRecipeInput.value = it },
                                label = { Text(stringResource(R.string.new_recipe_ai)) },
                                modifier = Modifier.weight(2f)
                            )
                            Button(
                                modifier = Modifier.weight(1f),
                                onClick = { actualMenuScreenViewModel.generateRecipeByAi(aiRecipeInput.value) }
                            ) {
                                Text(stringResource(R.string.button_add))
                            }
                        }

                    }
                    items(uiState.menus) { recipe ->
                        // Local state for this item: prefer cached full recipe if available
                        val cached = actualMenuScreenViewModel.getCachedRecipeWithImage(recipe.id)
                        val displayRecipeState = remember(recipe.id) { mutableStateOf(cached ?: recipe) }

                        // Wenn noch kein Bild vorhanden, lazy nachladen
                        LaunchedEffect(displayRecipeState.value.id) {
                            val dr = displayRecipeState.value
                            val hasImage = !dr.imageBase64.isNullOrEmpty() || !dr.image.isNullOrEmpty()
                            if (!hasImage) {
                                actualMenuScreenViewModel.loadRecipeImageById(recipe.id) { fullRecipe ->
                                    displayRecipeState.value = fullRecipe
                                }
                            }
                        }
                        Card(
                            modifier = Modifier
                                .padding(horizontalPadding, verticalPadding),
                            onClick = { onRecipeClick(recipe) }
                        ) {
                            val displayRecipe = displayRecipeState.value
                            MenuView(
                                displayRecipe.title,
                                subTitle = displayRecipe.subtitle ?: "",
                                labels = displayRecipe.labels?.map { label -> label.name } ?: emptyList(),
                                displayRecipe.duration.toString()
                            )
                            ImageView(
                                modifier = Modifier.padding(horizontalPadding, verticalPadding),
                                displayRecipe
                            )
                        }
                    }
                }
            }
        }
    }
}

