package com.bach.familyfresh.data

import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.HttpResponse
import org.openapitools.client.models.RecipeReadDto

class MenuRepository(private val client : DefaultApi = DefaultApi()) {

    suspend fun getActualMenu() : HttpResponse<List<RecipeReadDto>> {
         return client.getActualRecipes();
    }
}