package com.bach.familyfresh.features.actualmenu.bars

import android.media.MediaPlayer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.bach.familyfresh.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier, onClickNewRandomMenu: () -> Unit) {
    CenterAlignedTopAppBar(title =
    {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Aktuelles Menu", style = MaterialTheme.typography.titleLarge)
            Text("Würfel links ein neues Menü oder wähle die Gerichte einzeln aus.", style = MaterialTheme.typography.titleSmall)
        }
    }, navigationIcon =  { IconButton(onClick = { onClickNewRandomMenu() }) {
        Icon(
            painter = painterResource(R.drawable.casino_24px),
            contentDescription = null
        )
    } })
}