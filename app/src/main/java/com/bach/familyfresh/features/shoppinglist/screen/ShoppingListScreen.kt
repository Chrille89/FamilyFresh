package com.bach.familyfresh.features.shoppinglist.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bach.familyfresh.R
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.features.actualmenu.views.TabBarItem
import com.bach.familyfresh.features.actualmenu.views.TabView
import com.bach.familyfresh.features.shoppinglist.viewmodel.ShoppingListScreenViewModel
import com.bach.familyfresh.ui.theme.FamilyFreshTheme
import org.openapitools.client.models.RecipeReadDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    shoppingListScreenViewModel: ShoppingListScreenViewModel = viewModel(),
    onClickBack: () -> Unit) {
    Scaffold(
        topBar = {
        TopAppBar(
            title = { Text("Einkaufsliste")},
            navigationIcon = {
                IconButton(onClick = { onClickBack() }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack,null)
                }
            }
        )
    },
        bottomBar = {
            BottomAppBar {
                var tabBarItemShoppingListFirstRecipe = TabBarItem(
                    shoppingListScreenViewModel.menus.value[0].title,
                    selectedIcon = Icons.Filled.ShoppingCart,
                    unselectedIcon = Icons.Outlined.ShoppingCart)
                var tabBarItemShoppingListSecondRecipe = TabBarItem(
                    shoppingListScreenViewModel.menus.value[1].title,
                    selectedIcon = Icons.Filled.ShoppingCart,
                    unselectedIcon = Icons.Outlined.ShoppingCart)
                TabView(listOf(tabBarItemShoppingListFirstRecipe,tabBarItemShoppingListSecondRecipe), selectedTabIndex = 0) { }
            }
        }

        ) { innerPadding ->
        val menu = shoppingListScreenViewModel.menus.value;
        LazyColumn(modifier.padding(innerPadding).fillMaxSize()) {

            item {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    MenuView(
                        menu[0].title,
                        subTitle = menu[0].subtitle ?: "",
                        labels = menu[0].labels?.map { label -> label.value } ?: emptyList(),
                        menu[0].duration.toString() +" min"
                    )
                    AsyncImage(
                        model = menu[0].image,
                        contentDescription = null,
                    )
                }
            }

            item {
                Row(modifier.padding(horizontal = 10.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Zutaten", style = MaterialTheme.typography.headlineMedium )
                }
            }

            items(menu[0].ingredients ?: emptyList()) {
                item ->
                Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(false, onCheckedChange = {})
                    Text(item.amount.toString()+" "+item.unit?.value+" "+item.name)
                }
            }
        }
    }
}
