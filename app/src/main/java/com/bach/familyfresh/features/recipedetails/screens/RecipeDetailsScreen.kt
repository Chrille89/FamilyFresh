package com.bach.familyfresh.features.recipedetails.screens

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
                Text("Zubereitung")
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
         Column (Modifier.fillMaxSize().padding(innerPadding),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.spacedBy(10.dp)) {


                 Card(
                     modifier = Modifier.fillMaxSize()
                         .padding(10.dp)
                 ) {
                     MenuView(
                         "Gefüllte Paprikaschoten",
                         subTitle = "mit Kartoffeln",
                         labels = listOf("Schwein","Thermomix"),
                         "30 min"
                     )
                     AsyncImage(
                         contentScale = ContentScale.FillBounds,
                         model = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/E829412C-6272-4361-ADC1-DD016CBB03C1/Derivates/B0FA5272-D8BB-4E16-B56A-B9717A651432.jpg",
                         contentDescription = null,
                     )

                     Column {

                     }
                     Row(
                         Modifier.fillMaxWidth().padding(10.dp)) {
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
                         Modifier.fillMaxWidth().padding(10.dp)) {
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

             /*
             item {
                 Text("Paprikaschote zubereiten",style= MaterialTheme.typography.headlineSmall)
                 Text("Zwiebeln in den Mixtopf geben und 3 sek. Stufe 8 zerkleinern.")
                 Text("Hackfleisch, Ei, Semmelbrösel, Senf, Salz und Pfeffer zugeben und 10 sek. Stufe 4 mischen")
                 Text("Paprikaschote mit Hackfleisch füllen und rundherum anbraten.")
                 Text("Paprikaschoten in den Airfryer geben und 20 min 200 Grad frittieren. In dieser Zeit Kartoffeln kochen.")
             }

             item {




                 Text("Kartoffeln zubereiten",style= MaterialTheme.typography.headlineSmall)
                 Text("Kartoffeln schälen, kleinschneiden und ins Garkörbchen legen",)
                 Text("1l Wasser und Salz in den Mixtopf geben und 25 min. Varoma Stufe 1 dampfgaren.")
             }*/








    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    FamilyFreshTheme {
        RecipeDetailsScreen()
    }
}