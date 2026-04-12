package com.bach.familyfresh.ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.bach.familyfresh.R

@Composable
fun DurationView(modifier: Modifier = Modifier, duration: String) {
    Icon(
        imageVector = Icons.Outlined.AccessTime,
        contentDescription = null,
        modifier = Modifier.scale(0.5f),
        tint = MaterialTheme.colorScheme.primary
    )
    Text(
        text = "$duration ${stringResource(R.string.minutes)}",
        style = MaterialTheme.typography.bodySmall
    )
}