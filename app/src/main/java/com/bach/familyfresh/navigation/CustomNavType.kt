package com.bach.familyfresh.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.openapitools.client.models.RecipeReadDto

object CustomNavType {

    var recipeReadDtoNavType = object : NavType<RecipeReadDto>(isNullableAllowed = false){
        override fun get(bundle: Bundle, key: String): RecipeReadDto? {
            return Json.decodeFromString(bundle.getString(key) ?: "")
        }

        override fun parseValue(value: String): RecipeReadDto {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: RecipeReadDto): String {
            return Uri.encode(Json.encodeToString(value));
        }

        override fun put(bundle: Bundle, key: String, value: RecipeReadDto) {
            bundle.putString(key,Json.encodeToString(value))
        }

    }

}