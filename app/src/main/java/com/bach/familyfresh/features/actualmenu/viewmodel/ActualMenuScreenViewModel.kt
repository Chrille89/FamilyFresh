package com.bach.familyfresh.features.actualmenu.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bach.familyfresh.data.MenuRepository
import kotlinx.coroutines.launch

class ActualMenuScreenViewModel(private val menuRepository: MenuRepository = MenuRepository()) : ViewModel() {



    init {
        getActualMenu()
    }


     fun getActualMenu() {
        viewModelScope.launch {
            var recipe = menuRepository.getActualMenu().body().get(0)
            Log.d("ActualMenuScreenViewModel","Recipe: "+recipe.title);

        }
    }




}