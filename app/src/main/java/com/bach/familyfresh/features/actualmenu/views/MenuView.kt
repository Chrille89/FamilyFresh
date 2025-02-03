package com.bach.familyfresh.features.actualmenu.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun MenuView(title: String, subTitle: String, labels: List<String>) {
            Column(Modifier.padding(5.dp)) {
                Row {
                    labels.forEach {
                        Card(
                            Modifier.padding(1.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface)
                        ) {
                            Text(
                                it,
                                Modifier.padding(5.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(subTitle, style = MaterialTheme.typography.titleSmall)
            }

}