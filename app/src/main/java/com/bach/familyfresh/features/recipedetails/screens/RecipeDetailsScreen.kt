package com.bach.familyfresh.features.recipedetails.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bach.familyfresh.features.actualmenu.screens.ActualMenuScreen
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.ui.theme.FamilyFreshTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                Text("Gefüllte Paprikaschote")
            },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            null)
                    }
                }
            )
        }
    ) {
        innerPadding ->
         Column(Modifier.fillMaxSize().padding(innerPadding),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.spacedBy(10.dp)) {
             AsyncImage(
                 model = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/E829412C-6272-4361-ADC1-DD016CBB03C1/Derivates/B0FA5272-D8BB-4E16-B56A-B9717A651432.jpg",
                 contentDescription = null,
             )

             Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    Modifier.fillMaxWidth()) {
                    Column(Modifier.weight(1f)) {
                        Text("Brennwert", Modifier.fillMaxWidth(),fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        Text("1000 kCal",Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                    }

                    Column(Modifier.weight(1f)) {
                        Text("Gesättige Fettsäuren",Modifier.fillMaxWidth(),fontWeight = FontWeight.Bold,textAlign = TextAlign.Center)
                        Text("10g",Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                    }

                }

                 Row(
                     Modifier.fillMaxWidth()) {
                     Column(Modifier.weight(1f)) {
                         Text("Eiweiß",Modifier.fillMaxWidth(),fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                         Text("20 g",Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                     }

                     Column(Modifier.weight(1f)) {
                         Text("Zucker",Modifier.fillMaxWidth(),fontWeight = FontWeight.Bold,textAlign = TextAlign.Center)
                         Text("10g",Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                     }

                 }



             }

         }

    }


}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    FamilyFreshTheme {
        RecipeDetailsScreen()
    }
}