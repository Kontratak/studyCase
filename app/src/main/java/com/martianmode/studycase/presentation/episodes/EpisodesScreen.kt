package com.martianmode.studycase.presentation.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.martianmode.studycase.presentation.base.BaseScreen
import com.martianmode.studycase.presentation.episodes.component.EpisodeItem

@Composable
fun EpisodesScreen(navController: NavHostController) {
    val episodesViewModel = hiltViewModel<EpisodesViewModel>()

    LaunchedEffect(Unit){
        episodesViewModel.getEpisodes()
    }

    BaseScreen(navController,"Episodes") {
        LazyColumn(Modifier.background(MaterialTheme.colorScheme.primary), contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(episodesViewModel.episodesResponse?.results?.size ?: 0){ index ->
                EpisodeItem(model = episodesViewModel.episodesResponse?.results?.get(index)){

                }
            }
        }
    }
}