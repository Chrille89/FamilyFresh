package com.bach.familyfresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bach.familyfresh.features.actualmenu.screen.ActualMenuScreen
import com.bach.familyfresh.features.recipedetails.screen.RecipeDetailsScreen
import com.bach.familyfresh.features.recipedetails.viewmodel.RecipeDetailsScreenViewModel
import com.bach.familyfresh.features.recipelist.screen.RecipeListScreen
import com.bach.familyfresh.features.shoppinglist.screen.ShoppingListScreen
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
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavHost(
        navController,
        startDestination = Routes.ActualMenuRoute
        ) {
        composable<Routes.ActualMenuRoute> {
            ActualMenuScreen(
                onRecipeClick = { recipe ->
                navController.navigate(Routes.RecipeDetailRoute(recipe))
            }, onClickTab = { title ->
                when(title) {
                     "Gerichte ändern" ->  navController.navigate(Routes.RecipeListRoute)
                     "Einkaufsliste" ->  navController.navigate(Routes.ShoppingListRoute)
                }
                }, selectedTabIndex = selectedTabIndex)
        }
        composable<Routes.RecipeDetailRoute>(
            typeMap = mapOf(
                typeOf<RecipeReadDto>() to CustomNavType.recipeReadDtoNavType
        )
        ) {
            val data = it.toRoute<Routes.RecipeDetailRoute>();
            RecipeDetailsScreen(RecipeDetailsScreenViewModel(data.recipe)) {
                navController.popBackStack()
                selectedTabIndex = 0
            }
        }
        composable<Routes.RecipeListRoute> {
            RecipeListScreen() {
                navController.popBackStack()
                selectedTabIndex = 0
            }
        }
        composable<Routes.ShoppingListRoute> {
            ShoppingListScreen() {
                navController.popBackStack()
                selectedTabIndex = 0
            }
        }
    }


}