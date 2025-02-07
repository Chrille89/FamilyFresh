package com.bach.familyfresh.features.recipelist.viewmodel

import android.util.Log
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

    var menuUpdatesState: MutableState<RecipeUpdateStatus> =
        mutableStateOf(RecipeUpdateStatus.loading)

    val menuUpdates: ArrayList<RecipeReadDto> = ArrayList()

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
                }
            } catch (error: Throwable) {
                ActualMenuScreenStatus.error(error, "Error fetching data");
            }
        }
    }

    fun setNewRecipeForMenu(recipe: RecipeReadDto) {
        menuUpdates.add(recipe);
        if (menuUpdates.size == 2) {
            // PUT call
            viewModelScope.launch {
                try {
                    val response = menuRepository.updateMenu(menuUpdates)
                    if (response.success) {
                        menuUpdatesState.value = RecipeUpdateStatus.success(response.body())
                        menuUpdates.clear();
                    }
                } catch (error: Throwable) {
                    menuUpdatesState.value = RecipeUpdateStatus.error(error, "Error fetching data");
                }
            }
        }
    }
}