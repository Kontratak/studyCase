package com.martianmode.studycase.presentation.characters

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.martianmode.studycase.core.utils.Navigation
import com.martianmode.studycase.presentation.base.BaseScreen
import com.martianmode.studycase.presentation.characters.component.CharacterItem

@Composable
fun CharactersScreen(navController: NavHostController) {

    val charactersViewModel = hiltViewModel<CharactersViewModel>()

    LaunchedEffect(Unit){
        charactersViewModel.getCharacters()
    }

    BaseScreen(navController,title = "Characters") {
        LazyColumn {
            items(charactersViewModel.charactersResponse?.results?.size ?: 0){ index ->
                CharacterItem(model = charactersViewModel.charactersResponse?.results?.get(index)){
                    navController.navigate("${Navigation.CHARACTER_DETAILS.route}?id=$it")
                }
            }
        }
    }
}