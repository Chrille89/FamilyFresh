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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import com.bach.familyfresh.features.recipelist.viewmodel.RecipeListScreenViewModel
import com.bach.familyfresh.features.recipelist.viewmodel.RecipeUpdateStatus
import com.bach.familyfresh.ui.dialogs.InfoDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipesListScreenViewModel: RecipeListScreenViewModel = viewModel(),
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("Wähle 2 Gerichte aus") },
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
        when (val recipes = recipesListScreenViewModel.recipes.value) {
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
                var checkedItem by remember { mutableIntStateOf(0) }

                LazyColumn(modifier.padding(innerPadding)) {
                    items(recipes.menus) { recipe ->
                        Card(modifier
                            .height(120.dp)
                            .padding(horizontal = 10.dp, vertical = 5.dp)) {
                            Column {
                                Row(
                                    modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    var checked by remember { mutableStateOf(false) }
                                    Checkbox(
                                        checked = checked,
                                        onCheckedChange = {
                                            checked = !checked
                                            recipesListScreenViewModel.setNewRecipeForMenu(recipe)
                                        }
                                    )

                                    Column {
                                        Row {
                                            recipe.labels?.forEach {
                                                Card(
                                                    modifier.padding(5.dp),
                                                    border = BorderStroke(
                                                        1.dp,
                                                        MaterialTheme.colorScheme.surface
                                                    )
                                                ) {
                                                    Text(
                                                        it.value,
                                                        modifier.padding(5.dp),
                                                        style = MaterialTheme.typography.bodySmall
                                                    )
                                                }
                                            }
                                        }
                                        Row {
                                            AsyncImage(
                                                model = recipe.image,
                                                contentDescription = null
                                            )
                                            Column(modifier.padding(horizontal = 5.dp)) {
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