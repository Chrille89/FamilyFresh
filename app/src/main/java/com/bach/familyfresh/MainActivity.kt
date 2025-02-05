package com.bach.familyfresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bach.familyfresh.features.actualmenu.screen.ActualMenuScreen
import com.bach.familyfresh.features.recipedetails.screen.RecipeDetailsScreen
import com.bach.familyfresh.features.recipedetails.viewmodel.RecipeDetailsScreenViewModel
import com.bach.familyfresh.navigation.CustomNavType
import com.bach.familyfresh.navigation.Routes
import com.bach.familyfresh.ui.theme.FamilyFreshTheme
import kotlinx.serialization.Serializable
import org.openapitools.client.models.RecipeReadDto
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FamilyFreshTheme {
                FamilyFreshApp()
                }
            }
        }
}

@Composable
fun FamilyFreshApp(navController : NavHostController = rememberNavController()) {
    NavHost(
        navController,
        startDestination = Routes.ActualMenuRoute
        ) {
        composable<Routes.ActualMenuRoute> {
            ActualMenuScreen(onRecipeClick = { recipe ->
                navController.navigate(Routes.RecipeDetailRoute(recipe))
            })
        }
        composable<Routes.RecipeDetailRoute>(
            typeMap = mapOf(
                typeOf<RecipeReadDto>() to CustomNavType.recipeReadDtoNavType
        )
        ) {
            val data = it.toRoute<Routes.RecipeDetailRoute>();
            RecipeDetailsScreen(recipeDetailsScreenViewModel = RecipeDetailsScreenViewModel(data.recipe))
        }
    }


}