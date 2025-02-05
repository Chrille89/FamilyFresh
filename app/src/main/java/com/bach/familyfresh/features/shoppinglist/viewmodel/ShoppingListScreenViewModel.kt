package com.bach.familyfresh.features.shoppinglist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.openapitools.client.models.RecipeReadDto

class ShoppingListScreenViewModel(private var menu: List<RecipeReadDto>) : ViewModel() {

    var menuState = mutableStateOf(menu);

    fun getRecipeByIndex(index: Int): RecipeReadDto {
        if (index < menu.size) {
            return menuState.value[index]
        } else {
            // Exception
        }
        return menuState.value[0]
    }
}