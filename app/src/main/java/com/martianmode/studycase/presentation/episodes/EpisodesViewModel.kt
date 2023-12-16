package com.martianmode.studycase.presentation.episodes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse
import com.martianmode.studycase.data.model.charactersResponse.CharactersResponse
import com.martianmode.studycase.data.model.episodeDetailsResponse.EpisodeDetailsResponse
import com.martianmode.studycase.data.model.episodesResponse.EpisodesResponse
import com.martianmode.studycase.data.remote.ApiService
import com.martianmode.studycase.data.repository.ApiRepository
import com.martianmode.studycase.di.ApiModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    var episodesResponse by mutableStateOf<EpisodesResponse?>(null)
    var episodeDetailsResponse by mutableStateOf<EpisodeDetailsResponse?>(null)

    fun getEpisodes(){
        viewModelScope.launch{
            apiRepository.getEpisodes()?.let {response ->
                withContext(Dispatchers.Main){
                    episodesResponse = response
                }
            }
        }
    }

    fun getEpisodeDetails(id:Int?){
        id?.let {
            viewModelScope.launch{
                apiRepository.getEpisodeDetails(id)?.let {response ->
                    withContext(Dispatchers.Main){
                        episodeDetailsResponse = response
                    }
                }
            }
        }
    }
}