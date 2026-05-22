package com.bach.familyfresh.data

import com.bach.familyfresh.network.apis.DefaultApi
import com.bach.familyfresh.network.infrastructure.HttpResponse
import com.bach.familyfresh.network.models.RecipePromptDto
import com.bach.familyfresh.network.models.RecipeReadDto

class MenuRepository(private val client : DefaultApi = DefaultApi()) {

    suspend fun getActualMenu(random: Boolean) : HttpResponse<List<RecipeReadDto>> {
        val resp : HttpResponse<List<RecipeReadDto>> = client.getActualMenu(random)
        return resp
    }

    suspend fun getAllRecipes() : HttpResponse<List<RecipeReadDto>> {
        val resp : HttpResponse<List<RecipeReadDto>> = client.getAllRecipes()
        return resp
    }

    suspend fun updateMenu(menu: List<RecipeReadDto>) : HttpResponse<List<RecipeReadDto>> {
        val resp : HttpResponse<List<RecipeReadDto>> = client.updateMenu(menu);
        return resp
    }

    suspend fun deleteRecipeById(id: String) : HttpResponse<RecipeReadDto> {
        val resp : HttpResponse<RecipeReadDto> = client.deleteRecipeById(id)
        return resp
    }

    suspend fun getRecipeByAi(prompt: String) : HttpResponse<RecipeReadDto> {
        val resp : HttpResponse<RecipeReadDto> = client.createRecipesWithAI(RecipePromptDto(prompt))
        return resp
    }




}