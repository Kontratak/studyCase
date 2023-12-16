package com.martianmode.studycase.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.martianmode.studycase.core.utils.Navigation
import com.martianmode.studycase.presentation.characters.CharacterDetailsScreen
import com.martianmode.studycase.presentation.characters.CharactersScreen
import com.martianmode.studycase.presentation.episodes.EpisodesScreen
import com.martianmode.studycase.presentation.locations.LocationDetailsScreen
import com.martianmode.studycase.presentation.locations.LocationsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        Navigation.CHARACTERS.route
    ) {
        composable(route = Navigation.CHARACTERS.route) {
            CharactersScreen(navController)
        }
        composable(
            route = "${Navigation.CHARACTER_DETAILS.route}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ),
        ) { entry ->
            CharacterDetailsScreen(navController,entry.arguments?.getInt("id"))
        }
        composable(route = Navigation.EPISODES.route) {
            EpisodesScreen(navController)
        }
        composable(route = Navigation.LOCATIONS.route) {
            LocationsScreen(navController)
        }
        composable(
            route = "${Navigation.LOCATION_DETAILS.route}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ),
        ) { entry ->
            LocationDetailsScreen(navController,entry.arguments?.getInt("id"))
        }
    }
}
