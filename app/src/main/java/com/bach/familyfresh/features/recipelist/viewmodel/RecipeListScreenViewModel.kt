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

class RecipeListScreenViewModel(private val menuRepository: MenuRepository = MenuRepository()) : ViewModel() {

    val recipes : MutableState<ActualMenuScreenStatus> =  mutableStateOf(ActualMenuScreenStatus.loading)

    val menuUpdates : ArrayList<RecipeReadDto> = ArrayList()

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() {
        viewModelScope.launch {
            try {
                val response = menuRepository.getAllRecipes();
                if(response.success) {
                    val responseData = response.body();
                    recipes.value = ActualMenuScreenStatus.success(responseData)
                }
            } catch(error: Throwable) {
                ActualMenuScreenStatus.error(error,"Error fetching data");
            }
        }
    }

    fun setNewRecipeForMenu(recipe: RecipeReadDto) : ArrayList<RecipeReadDto> {
        menuUpdates.add(recipe);
        if(menuUpdates.size == 2) {
            // PUT call
            menuUpdates.clear()
        }
        return menuUpdates;
    }
}