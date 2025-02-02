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
import org.openapitools.client.infrastructure.HttpResponse
import org.openapitools.client.models.RecipeReadDto

sealed interface ActualMenuScreenStatus {
    data object loading: ActualMenuScreenStatus
    data class success(var menus: List<RecipeReadDto>) : ActualMenuScreenStatus
    data class error(var error: Throwable, var errorMsg : String) : ActualMenuScreenStatus

}

class ActualMenuScreenViewModel(private val menuRepository: MenuRepository = MenuRepository()) : ViewModel() {

    val menus : MutableState<ActualMenuScreenStatus> =  mutableStateOf(ActualMenuScreenStatus.loading)

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

    private fun getSensor() {

    }
}