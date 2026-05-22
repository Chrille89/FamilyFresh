package com.bach.familyfresh.features.shoppinglist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bach.familyfresh.network.models.RecipeReadDto

class ShoppingListScreenViewModel(private var recipe: RecipeReadDto) : ViewModel() {

    var recipeState = mutableStateOf(recipe);

}