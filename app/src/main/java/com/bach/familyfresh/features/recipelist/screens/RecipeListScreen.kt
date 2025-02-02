package com.bach.familyfresh.features.recipelist.screens

import androidx.collection.intIntMapOf
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bach.familyfresh.features.actualmenu.screens.ActualMenuScreen
import com.bach.familyfresh.ui.theme.FamilyFreshTheme

@Composable
fun RecipeListScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        LazyColumn(modifier.padding(innerPadding)) {
            item {

                Card {
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
                            Column {
                                Row {
                                    Card(
                                        Modifier.padding(1.dp),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface)
                                    ) {
                                        Text(
                                            "Vegetarisch",
                                            Modifier.padding(5.dp),
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }

                                Text("Senfeier", style = MaterialTheme.typography.titleLarge)
                                Text("mit Kartoffeln", style = MaterialTheme.typography.titleSmall)
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