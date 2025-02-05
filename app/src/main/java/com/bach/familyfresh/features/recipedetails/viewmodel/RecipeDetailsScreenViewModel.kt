package com.bach.familyfresh.features.recipedetails.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.openapitools.client.models.RecipeReadDto

class RecipeDetailsScreenViewModel(recipe: RecipeReadDto) : ViewModel() {

    var recipeState = mutableStateOf(recipe)

}