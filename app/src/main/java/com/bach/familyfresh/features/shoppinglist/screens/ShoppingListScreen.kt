package com.bach.familyfresh.features.shoppinglist.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
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
import coil3.compose.AsyncImage
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.features.recipelist.screens.RecipeListScreen
import com.bach.familyfresh.ui.theme.FamilyFreshTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(modifier: Modifier = Modifier) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Einkaufsliste")},
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack,null)
                }
            }
        )
    }) { innerPadding ->
        Column(modifier.padding(innerPadding).fillMaxSize()) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                MenuView(
                    "Gefüllte Paprikaschote",
                    subTitle = "mit Kartoffeln",
                    labels = listOf("Schwein"),
                )
                AsyncImage(
                    model = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/E829412C-6272-4361-ADC1-DD016CBB03C1/Derivates/B0FA5272-D8BB-4E16-B56A-B9717A651432.jpg",
                    contentDescription = null,
                )
            }

            Row(modifier.padding(horizontal = 10.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("Zutaten", style = MaterialTheme.typography.headlineMedium )
            }


            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(false, onCheckedChange = {})
                Text("2 Paprika")
            }

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(false, onCheckedChange = {})
                Text("500g gemischtes Hackfleisch")
            }

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(false, onCheckedChange = {})
                Text("500g Kartoffeln")
            }

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(false, onCheckedChange = {})
                Text("1 Ei")
            }

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(false, onCheckedChange = {})
                Text("50g Semmelbrösel")
            }

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(false, onCheckedChange = {})
                Text("1 Esslöfel Senf")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListScreenPreview() {
    FamilyFreshTheme {
        ShoppingListScreen()
    }
}