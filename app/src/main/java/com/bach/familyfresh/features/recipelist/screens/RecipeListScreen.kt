package com.bach.familyfresh.features.recipelist.screens

import androidx.collection.intIntMapOf
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bach.familyfresh.features.actualmenu.bars.TopAppBar
import com.bach.familyfresh.features.actualmenu.screens.ActualMenuScreen
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.features.recipelist.viewmodel.RecipeListScreenViewModel
import com.bach.familyfresh.ui.theme.FamilyFreshTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    recipesListScreenViewModel: RecipeListScreenViewModel = viewModel(),
    modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("Aktuelle Auswahl ändern")},
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null)
                    }
                }
            )
    }

    ) { innerPadding ->
        when (val recipes = recipesListScreenViewModel.recipes.value) {
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
                LazyColumn(modifier.padding(innerPadding)) {
                    items(recipes.menus) {
                        Card(modifier.height(120.dp).padding(horizontal = 10.dp, vertical = 10.dp)) {
                            Column {
                                Row(
                                    modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = false,
                                        onCheckedChange = {  }
                                    )
                                    AsyncImage(
                                        model = "https://www.malteskitchen.de/wp-content/uploads/2025/01/senfeier-5-500x500.webp",
                                        contentDescription = null
                                    )
                                    MenuView("Senfeier","mit Kartoffeln", listOf("Vegetarian"))
                                }
                            }
                        }
                    }
                }
            }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreview() {
    FamilyFreshTheme {
        RecipeListScreen()
    }
}