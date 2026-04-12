package com.bach.familyfresh.features.actualmenu.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bach.familyfresh.R
import com.bach.familyfresh.ui.views.DurationView

@Composable
fun MenuView(title: String, subTitle: String, labels: List<String>, duration: String) {
    Column(Modifier.padding(5.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                DurationView(duration = duration)
            }
        }
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(subTitle, style = MaterialTheme.typography.titleSmall)
    }
}