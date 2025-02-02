package com.bach.familyfresh.data

import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.HttpResponse
import org.openapitools.client.models.RecipeReadDto

class MenuRepository(private val client : DefaultApi = DefaultApi()) {

    suspend fun getActualMenu(random: Boolean) : HttpResponse<List<RecipeReadDto>> {
        var resp : HttpResponse<List<RecipeReadDto>> = client.getActualMenu(random);
        return resp;
    }
}