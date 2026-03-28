package com.bach.familyfresh.features.recipelist.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bach.familyfresh.R
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import com.bach.familyfresh.features.recipelist.viewmodel.RecipeListScreenViewModel
import com.bach.familyfresh.features.recipelist.viewmodel.RecipeUpdateStatus
import com.bach.familyfresh.features.recipelist.views.DropDownLabelFilter
import com.bach.familyfresh.ui.dialogs.InfoDialog
import org.openapitools.client.models.RecipeReadDto

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
                Column(modifier
                    .fillMaxSize()
                    .padding(innerPadding)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DropDownLabelFilter(
                            Modifier.padding(horizontalPadding,verticalPadding),
                            labels = RecipeReadDto.Labels.values().map { it.value }.toMutableList(),
                            { label -> recipesListScreenViewModel.filterRecipesByLabel(label)
                            })
                        Text(
                            text = "${recipes.menus.size} ${stringResource(R.string.meals)}",
                            Modifier.padding(horizontalPadding,verticalPadding),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    LazyColumn {
                        items(recipes.menus) { recipe ->
                            Card(Modifier
                                .height(120.dp)
                                .padding(horizontalPadding,verticalPadding)) {
                                Column {
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        var checked = recipesListScreenViewModel.menuUpdates.value.contains(recipe)
                                        Checkbox(
                                            checked = checked,
                                            onCheckedChange = { newChecked ->
                                                checked = !checked
                                                if (newChecked) {
                                                    recipesListScreenViewModel.setNewRecipeForMenu(recipe)
                                                } else {
                                                    recipesListScreenViewModel.menuUpdates.value.remove(recipe)
                                                }
                                            }
                                        )

                                        Column {
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
                                            Row {
                                                AsyncImage(
                                                    modifier = Modifier.padding(horizontalPadding,verticalPadding),
                                                    model = recipe.image,
                                                    contentDescription = null
                                                )
                                                Column(Modifier.padding(horizontal = 5.dp)) {
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