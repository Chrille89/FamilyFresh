package com.bach.familyfresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bach.familyfresh.features.actualmenu.screens.ActualMenuScreen
import com.bach.familyfresh.features.recipelist.screens.RecipeListScreen
import com.bach.familyfresh.features.shoppinglist.screens.ShoppingListScreen
import com.bach.familyfresh.ui.theme.FamilyFreshTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FamilyFreshTheme {
                ShoppingListScreen()
                }
            }
        }
}