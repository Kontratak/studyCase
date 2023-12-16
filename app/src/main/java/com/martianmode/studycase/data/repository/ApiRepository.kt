package com.martianmode.studycase.data.repository

import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse
import com.martianmode.studycase.data.model.charactersResponse.CharactersResponse
import com.martianmode.studycase.data.model.charactersResponse.MultipleCharactersResponse
import com.martianmode.studycase.data.model.episodeDetailsResponse.EpisodeDetailsResponse
import com.martianmode.studycase.data.model.episodesResponse.EpisodesResponse
import com.martianmode.studycase.data.model.locationDetailsResponse.LocationDetailsResponse
import com.martianmode.studycase.data.model.locationsResponse.LocationsResponse
import com.martianmode.studycase.data.remote.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService : ApiService) {

    suspend fun getCharacters(): CharactersResponse? {
        return apiService.getCharacters().body()
    }

    suspend fun getCharacterDetails(id: Int): CharacterDetailsResponse? {
        return apiService.getCharacterDetails(id).body()
    }

    suspend fun getEpisodes(): EpisodesResponse? {
        return apiService.getEpisodes().body()
    }

    suspend fun getEpisodeDetails(id: Int): EpisodeDetailsResponse? {
        return apiService.getEpisodeDetails(id).body()
    }

    suspend fun getLocations(): LocationsResponse? {
        return apiService.getLocations().body()
    }

    suspend fun getLocationDetails(id: Int): LocationDetailsResponse? {
        return apiService.getLocationDetails(id).body()
    }
        suspend fun getMultipleCharacters(characterIds : String?) : MultipleCharactersResponse? {
        characterIds?.let { return apiService.getCharacterDetails(characterIds).body() }
        return null
    }

}