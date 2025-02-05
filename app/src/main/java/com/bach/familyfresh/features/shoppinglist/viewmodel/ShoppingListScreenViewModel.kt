package com.bach.familyfresh.features.shoppinglist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.openapitools.client.models.RecipeReadDto

class ShoppingListScreenViewModel(menu: List<RecipeReadDto>) : ViewModel() {

    var menus = mutableStateOf(menu)

}