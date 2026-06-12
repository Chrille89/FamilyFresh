package com.bach.familyfresh.features.recipelist.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bach.familyfresh.R
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import com.bach.familyfresh.features.recipelist.viewmodel.RecipeListScreenViewModel
import com.bach.familyfresh.features.recipelist.viewmodel.RecipeUpdateStatus
import com.bach.familyfresh.features.recipelist.views.DropDownLabelFilter
import com.bach.familyfresh.ui.dialogs.InfoDialog
import com.bach.familyfresh.ui.views.DurationView
import com.bach.familyfresh.ui.views.ImageView
import com.bach.familyfresh.network.models.RecipeReadDto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipesListScreenViewModel: RecipeListScreenViewModel = viewModel(),
    onClickBack: () -> Unit
) {
    val horizontalPadding = 5.dp;
    val verticalPadding = 5.dp;
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text(stringResource(R.string.title_change_meals)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onClickBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },

        ) { innerPadding ->
        if (recipesListScreenViewModel.menuUpdatesState.value is RecipeUpdateStatus.success) {
            InfoDialog { onClickBack() }
        }
        when (val recipes = recipesListScreenViewModel.actualVisibleRecipes.value) {
            is ActualMenuScreenStatus.loading ->
                Column(
                    modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }

            is ActualMenuScreenStatus.error -> Text("Error")
            is ActualMenuScreenStatus.success -> {
                Column(
                    modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    DropDownLabelFilter(
                        Modifier.padding(horizontalPadding, verticalPadding),
                        labels = RecipeReadDto.Labels.entries.map { it.value }.toMutableList(),
                        { label ->
                            recipesListScreenViewModel.filterRecipesByLabel(label)
                        }
                    )
                    TextField(
                        value = recipesListScreenViewModel.ingredientSearchQuery.value,
                        onValueChange = { newQuery ->
                            recipesListScreenViewModel.filterRecipesByIngredient(newQuery)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontalPadding, verticalPadding),
                        label = { Text(stringResource(R.string.search_ingredients)) },
                    )
                    Text(
                        text = "${recipes.menus.size} ${stringResource(R.string.meals)}: ",
                        Modifier.padding(horizontalPadding,verticalPadding),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    LazyColumn {
                        items(recipes.menus) { recipe ->
                            // Lazy Loading: Lade Image nur wenn Card sichtbar wird
                            var loadedRecipe by remember { mutableStateOf(recipe) }

                            LaunchedEffect(recipe.id) {
                                // Prüfe zuerst Cache
                                val cachedRecipe = recipesListScreenViewModel.getCachedRecipeWithImage(recipe.id)
                                if (cachedRecipe != null) {
                                    loadedRecipe = cachedRecipe
                                } else {
                                    // Lade Image asynchron
                                    recipesListScreenViewModel.loadRecipeImageById(recipe.id) { fullRecipe ->
                                        loadedRecipe = fullRecipe
                                    }
                                }
                            }

                            Card(Modifier
                                .height(120.dp)
                                .padding(horizontalPadding,verticalPadding)) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    // AccessTime-Icon oben rechts mit Text
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = horizontalPadding)
                                            .align(Alignment.BottomEnd),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        DurationView(duration = recipe.duration.toString())
                                    }
                                    // Hauptinhalt
                                    Row(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = recipesListScreenViewModel.menuUpdates.value.contains(recipe),
                                            onCheckedChange = { newChecked ->
                                                if (newChecked) {
                                                    recipesListScreenViewModel.setNewRecipeForMenu(recipe)
                                                } else {
                                                    recipesListScreenViewModel.menuUpdates.value.remove(recipe)
                                                }
                                            }
                                        )

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxHeight()
                                        ) {
                                            // Labels
                                            Row {
                                                recipe.labels?.forEach {
                                                    Card(
                                                        Modifier.padding(5.dp),
                                                        border = BorderStroke(
                                                            1.dp,
                                                            MaterialTheme.colorScheme.surface
                                                        )
                                                    ) {
                                                        Text(
                                                            it.value,
                                                            Modifier.padding(5.dp),
                                                            style = MaterialTheme.typography.bodySmall
                                                        )
                                                    }
                                                }
                                            }
                                            // Bild und Text (mit lazy-geladenem Image)
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .fillMaxHeight(),
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                ImageView(
                                                    modifier = Modifier.padding(horizontalPadding, verticalPadding),
                                                    loadedRecipe
                                                )
                                                Column(
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .fillMaxHeight()
                                                        .padding(horizontal = 4.dp),
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Text(
                                                        recipe.title,
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                    Text(
                                                        recipe.subtitle ?: "",
                                                        style = MaterialTheme.typography.bodySmall
                                                    )
                                                }
                                            }
                                        }

                                        // Delete-Icon vertikal zentriert
                                        IconButton(
                                            onClick = {
                                                recipesListScreenViewModel.deleteRecipeById(recipe.id)
                                            },
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Delete,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}