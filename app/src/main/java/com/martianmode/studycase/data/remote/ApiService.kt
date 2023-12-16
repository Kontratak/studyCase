package com.martianmode.studycase.data.remote

import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse
import com.martianmode.studycase.data.model.charactersResponse.CharactersResponse
import com.martianmode.studycase.data.model.charactersResponse.MultipleCharactersResponse
import com.martianmode.studycase.data.model.episodeDetailsResponse.EpisodeDetailsResponse
import com.martianmode.studycase.data.model.episodesResponse.EpisodesResponse
import com.martianmode.studycase.data.model.locationDetailsResponse.LocationDetailsResponse
import com.martianmode.studycase.data.model.locationsResponse.LocationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponse?>

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id : Int): Response<CharacterDetailsResponse?>

    @GET("character/{characterIds}")
    suspend fun getCharacterDetails(@Path("characterIds") id : String): Response<MultipleCharactersResponse?>

    @GET("episode")
    suspend fun getEpisodes(): Response<EpisodesResponse?>

    @GET("episode/{id}")
    suspend fun getEpisodeDetails(@Path("id") id : Int): Response<EpisodeDetailsResponse?>

    @GET("location")
    suspend fun getLocations(): Response<LocationsResponse?>

    @GET("location/{id}")
    suspend fun getLocationDetails(@Path("id") id : Int): Response<LocationDetailsResponse?>
}