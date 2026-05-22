package com.bach.familyfresh.navigation

import kotlinx.serialization.Serializable
import com.bach.familyfresh.network.models.RecipeReadDto

class Routes {

    @Serializable
    data object ActualMenuRoute

    @Serializable
    data class RecipeDetailRoute(var recipe: RecipeReadDto)

    @Serializable
    data object RecipeListRoute

    @Serializable
    data class ShoppingListRoute(var recipe: RecipeReadDto)
}