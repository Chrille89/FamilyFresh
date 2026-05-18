package com.bach.familyfresh.features.recipelist.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bach.familyfresh.data.MenuRepository
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import kotlinx.coroutines.launch
import org.openapitools.client.models.RecipeReadDto

sealed interface RecipeUpdateStatus {
    data object loading : RecipeUpdateStatus
    data class success(var menus: List<RecipeReadDto>) : RecipeUpdateStatus
    data class error(var error: Throwable, var errorMsg: String) : RecipeUpdateStatus

}

class RecipeListScreenViewModel(private val menuRepository: MenuRepository = MenuRepository()) :
    ViewModel() {

    val recipes: MutableState<ActualMenuScreenStatus> =
        mutableStateOf(ActualMenuScreenStatus.loading)

    val actualVisibleRecipes: MutableState<ActualMenuScreenStatus> =
        mutableStateOf(ActualMenuScreenStatus.loading)

    var menuUpdatesState: MutableState<RecipeUpdateStatus> =
        mutableStateOf(RecipeUpdateStatus.loading)

    val menuUpdates: MutableState<MutableList<RecipeReadDto>> = mutableStateOf(mutableListOf())

    val ingredientSearchQuery: MutableState<String> = mutableStateOf("")

    val selectedLabel: MutableState<String> = mutableStateOf("Alle")

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() {
        viewModelScope.launch {
            try {
                val response = menuRepository.getAllRecipes();
                if (response.success) {
                    val responseData = response.body();
                    recipes.value = ActualMenuScreenStatus.success(responseData)
                    actualVisibleRecipes.value =  ActualMenuScreenStatus.success(responseData)
                }
            } catch (error: Throwable) {
                ActualMenuScreenStatus.error(error, "Error fetching data");
            }
        }
    }

    fun filterRecipesByLabel(label: String) {
        selectedLabel.value = label
        applyFilters()
    }

    fun filterRecipesByIngredient(query: String) {
        ingredientSearchQuery.value = query
        applyFilters()
    }

    private fun applyFilters() {
        val allRecipes = (recipes.value as? ActualMenuScreenStatus.success)?.menus ?: return
        
        // Label-Filter anwenden
        var filteredRecipes = allRecipes
        if (selectedLabel.value != "Alle") {
            filteredRecipes = filteredRecipes.filter { recipe ->
                recipe.labels?.map { it.value }?.contains(selectedLabel.value) ?: false
            }
        }

        // Zutaten-Filter anwenden
        if (ingredientSearchQuery.value.isNotEmpty()) {
            val searchIngredients = ingredientSearchQuery.value.split(",").map { it.trim() }.filter { it.isNotEmpty() }

            filteredRecipes = filteredRecipes.filter { recipe ->
                // Prüfe, dass das Rezept ALLE gesuchten Zutaten enthält
                searchIngredients.all { searchTerm ->
                    recipe.ingredients?.any { ingredient ->
                        ingredient.name?.contains(searchTerm, ignoreCase = true) ?: false
                    } ?: false
                }
            }
        }
        actualVisibleRecipes.value = ActualMenuScreenStatus.success(filteredRecipes)
    }

    fun setNewRecipeForMenu(recipe: RecipeReadDto) {
        val newList = menuUpdates.value.toMutableList().apply { add(recipe) }
        menuUpdates.value = newList
        if (menuUpdates.value.size == 2) {
            // PUT call
            viewModelScope.launch {
                try {
                    val response = menuRepository.updateMenu(menuUpdates.value)
                    if (response.success) {
                        menuUpdatesState.value = RecipeUpdateStatus.success(response.body())
                        menuUpdates.value = mutableListOf()
                    } else {
                        // Handle error, maybe revert or something
                    }
                } catch (error: Throwable) {
                    menuUpdatesState.value = RecipeUpdateStatus.error(error, "Error fetching data");
                }
            }
        }
    }

    fun deleteRecipeById(id: String) {
        viewModelScope.launch {
            try {
                val response = menuRepository.deleteRecipeById(id)
                if (response.success) {
                    val responseData = response.body();
                    val updatedRecipes = (recipes.value as ActualMenuScreenStatus.success).menus.filter { it.id != responseData.id }
                    recipes.value = ActualMenuScreenStatus.success(updatedRecipes)
                    actualVisibleRecipes.value =  ActualMenuScreenStatus.success(updatedRecipes)
                }
            } catch (error: Throwable) {
                ActualMenuScreenStatus.error(error, "Error delete recipe with id $id");
            }
        }
    }
}