package com.bach.familyfresh.features.actualmenu.viewmodel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bach.familyfresh.data.MenuRepository
import kotlinx.coroutines.launch
import com.bach.familyfresh.network.models.RecipeReadDto

sealed interface ActualMenuScreenStatus {
    data object loading: ActualMenuScreenStatus
    data class success(var menus: List<RecipeReadDto>) : ActualMenuScreenStatus
    data class error(var error: Throwable, var errorMsg : String) : ActualMenuScreenStatus

}

class ActualMenuScreenViewModel(private val menuRepository: MenuRepository = MenuRepository()) : ViewModel() {

    var menus : MutableState<ActualMenuScreenStatus> =  mutableStateOf(ActualMenuScreenStatus.loading)

    // Cache für geladene Images (RecipeId -> RecipeReadDto mit vollständigen Daten)
    private val imageCache = mutableMapOf<String, RecipeReadDto>()

    init {
        getActualMenu(false)
    }

    fun getActualMenu(random: Boolean) {
        viewModelScope.launch {
            try {
                val response = menuRepository.getActualMenu(random);
                if(response.success) {
                    val responseData = response.body();
                    menus.value = ActualMenuScreenStatus.success(listOf( responseData[0],responseData[1]))
                    Log.d("ActualMenuScreenViewModel","Recipe: "+responseData[0].title);
                }
            } catch(error: Throwable) {
                ActualMenuScreenStatus.error(error,"Error fetching data");
            }
        }
    }

    /**
     * Lazy Loading: Lädt ein Rezept mit vollständigen Daten (inklusive Image)
     * Wird nur aufgerufen, wenn der User das Image braucht
     */
    fun loadRecipeImageById(recipeId: String, onImageLoaded: (RecipeReadDto) -> Unit) {
        // Prüfe Cache zuerst
        imageCache[recipeId]?.let {
            onImageLoaded(it)
            return
        }

        viewModelScope.launch {
            try {
                val response = menuRepository.getRecipeImageById(recipeId)
                if (response.success) {
                    val fullRecipe = response.body()
                    imageCache[recipeId] = fullRecipe
                    // Aktualisiere die aktuelle Menü-State, falls das Rezept dort enthalten ist
                    val current = menus.value as? ActualMenuScreenStatus.success
                    if (current != null) {
                        val updated = current.menus.map { if (it.id == fullRecipe.id) fullRecipe else it }
                        menus.value = ActualMenuScreenStatus.success(updated)
                    }
                    onImageLoaded(fullRecipe)
                }
            } catch (error: Throwable) {
                println("Error loading image for recipe $recipeId: ${error.message}")
            }
        }
    }

    /**
     * Gibt das gecachte Rezept mit Image zurück, falls vorhanden
     */
    fun getCachedRecipeWithImage(recipeId: String): RecipeReadDto? {
        return imageCache[recipeId]
    }

    fun generateRecipeByAi(prompt : String) {
        Log.d("ActualMenuScreenViewModel","Generating recipe with prompt: "+prompt);
        viewModelScope.launch {
            try {
                val current = menus.value as? ActualMenuScreenStatus.success
                if(current == null) {
                    Log.i("ActualMenuScreenViewModel","Current menu is not in success state, cannot generate recipe");
                    return@launch
                }
                menus.value = ActualMenuScreenStatus.loading
                val response = menuRepository.getRecipeByAi(prompt);
                if(response.success) {
                    val responseData = response.body();
                    menus.value = ActualMenuScreenStatus.success(listOf( responseData,current.menus[1]))
                    Log.d("ActualMenuScreenViewModel","Recipe: "+responseData);
                }
            } catch(error: Throwable) {
                ActualMenuScreenStatus.error(error,"Error fetching data");
            }
        }
    }
}