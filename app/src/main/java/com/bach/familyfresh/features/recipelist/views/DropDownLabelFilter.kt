package com.bach.familyfresh.features.recipelist.views

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.openapitools.client.models.RecipeReadDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownLabelFilter(
    modifier: Modifier = Modifier,
    labels : List<String>,
    onSelectedLabel: (String) -> Unit
    ) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(labels[0]) }

    LaunchedEffect(Unit) {
        onSelectedLabel(selectedText)
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text("Label auswählen") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Alle") },
                onClick = {
                    onSelectedLabel("All")
                    selectedText = "Alle"
                    expanded = false
                }
            )
            labels.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onSelectedLabel(selectionOption)
                        selectedText = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}