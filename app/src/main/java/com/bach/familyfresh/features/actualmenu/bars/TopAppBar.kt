package com.bach.familyfresh.features.actualmenu.bars

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title =
    {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Aktuelles Menu", style = MaterialTheme.typography.titleLarge)
            Text("Wähle ein Gericht aus oder ändere deine Gerichte.", style = MaterialTheme.typography.titleSmall)
        }
    }, navigationIcon =  { IconButton(onClick = {}) {
        Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
    } })
}