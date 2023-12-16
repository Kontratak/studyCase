package com.martianmode.studycase.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.martianmode.studycase.core.utils.BottomNavItems


@Composable
fun BottomBar(navController : NavHostController) {
    BottomNavigation(Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)), backgroundColor = MaterialTheme.colorScheme.onPrimary, contentColor = MaterialTheme.colorScheme.secondary) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItems.values().forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                selectedContentColor = MaterialTheme.colorScheme.secondary,
                unselectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.name) },
            )
        }
    }
}