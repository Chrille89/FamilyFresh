package com.bach.familyfresh.navigation

import kotlinx.serialization.Serializable
import org.openapitools.client.models.RecipeReadDto

class Routes {

    @Serializable
    data object ActualMenuRoute

    @Serializable
    data class RecipeDetailRoute(var recipe: RecipeReadDto)

    @Serializable
    data object RecipeListRoute

    @Serializable
    data object ShoppingListRoute
}