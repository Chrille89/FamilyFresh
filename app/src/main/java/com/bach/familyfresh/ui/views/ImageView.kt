package com.bach.familyfresh.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.bach.familyfresh.utils.decodeBase64ToBitmap
import org.openapitools.client.models.RecipeReadDto

@Composable
fun ImageView(
    modifier: Modifier = Modifier,
    recipe: RecipeReadDto
) {
    if (!recipe.image.isNullOrEmpty()) {
        AsyncImage(
            modifier = modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            model = recipe.image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    } else if (!recipe.imageBase64.isNullOrEmpty()) {
            Image(
                bitmap = decodeBase64ToBitmap(recipe.imageBase64),
                contentDescription = null,
                modifier = modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
}