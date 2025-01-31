package com.bach.familyfresh.features.actualmenu.bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import com.bach.familyfresh.features.actualmenu.views.TabBarItem
import com.bach.familyfresh.features.actualmenu.views.TabView

@Composable
fun BottomAppBar() {
    androidx.compose.material3.BottomAppBar {
        val homeTab = TabBarItem(
            title = "Menüs",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        )
        val changeMenuTab =  TabBarItem(
            title = "Gerichte ändern",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        )
        val alertsTab = TabBarItem(
            title = "Einkaufsliste",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications,
            badgeAmount = 7
        )
        TabView(listOf(homeTab, changeMenuTab, alertsTab))
    }
}