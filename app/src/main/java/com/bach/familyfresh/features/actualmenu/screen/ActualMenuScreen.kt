package com.bach.familyfresh.features.actualmenu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bach.familyfresh.R
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenStatus
import com.bach.familyfresh.features.actualmenu.viewmodel.ActualMenuScreenViewModel
import com.bach.familyfresh.features.actualmenu.views.MenuView
import com.bach.familyfresh.features.actualmenu.views.TabBarItem
import com.bach.familyfresh.features.actualmenu.views.TabView
import com.bach.familyfresh.ui.views.ImageView
import org.openapitools.client.models.RecipeReadDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualMenuScreen(
    actualMenuScreenViewModel: ActualMenuScreenViewModel = viewModel(),
    onRecipeClick: (recipe: RecipeReadDto) -> Unit,
    onClickTab: (title: String, menu: List<RecipeReadDto>) -> Unit,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier
) {
    val horizontalPadding = 10.dp;
    val verticalPadding = 10.dp;
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title =
            {
                Column(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.title_actual_menu), style = MaterialTheme.typography.titleLarge)
                }
            })
        },
        bottomBar = {
            val menuTab = TabBarItem(
                title = stringResource(R.string.tab_menus),
                selectedIcon = ImageVector.vectorResource(R.drawable.restaurant_24px),
                unselectedIcon = ImageVector.vectorResource(R.drawable.restaurant_24px)
            )
            val changeMenuTab =  TabBarItem(
                title = stringResource(R.string.tab_change_meals),
                selectedIcon = ImageVector.vectorResource(R.drawable.change_circle_24px),
                unselectedIcon = ImageVector.vectorResource(R.drawable.change_circle_24px)
            )
            TabView(listOf(menuTab, changeMenuTab),selectedTabIndex) { tabTitle ->
                onClickTab(tabTitle,(actualMenuScreenViewModel.menus.value as ActualMenuScreenStatus.success).menus)
            }
        }
    ) { innerPadding ->
        when (val uiState = actualMenuScreenViewModel.menus.value) {
            is ActualMenuScreenStatus.loading ->
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }

            is ActualMenuScreenStatus.error -> Text("Error")
            is ActualMenuScreenStatus.success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(uiState.menus) { recipe ->
                        Card(
                            modifier = Modifier
                                .padding(horizontalPadding, verticalPadding),
                            onClick = { onRecipeClick(recipe) }
                        ) {
                            MenuView(
                                recipe.title,
                                subTitle = recipe.subtitle ?: "",
                                labels = recipe.labels?.map { label -> label.name } ?: emptyList(),
                                recipe.duration.toString()
                            )
                            ImageView(
                                modifier = Modifier.padding(horizontalPadding, verticalPadding),
                                recipe
                            )
                        }
                    }
                }
            }
        }
    }
}

