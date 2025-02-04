package com.bach.familyfresh.features.recipedetails.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.bach.familyfresh.features.actualmenu.views.MenuView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen() {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Gefüllte Paprikaschote")
            })
        }
    ) {
        innerPadding ->
         Column(Modifier.fillMaxSize().padding(innerPadding)) {
             MenuView(
                 menu.title,
                 subTitle = menu.subtitle ?: "",
                 labels = menu.labels?.map { label -> label.name } ?: emptyList(),
             )
             AsyncImage(
                 modifier = Modifier.fillMaxSize(),
                 model = menu.image,
                 contentScale = ContentScale.FillBounds,
                 contentDescription = null,
             )

         }

    }


}