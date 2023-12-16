package com.martianmode.studycase.presentation.locations

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martianmode.studycase.core.utils.getCharacterIdFromPath
import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse
import com.martianmode.studycase.data.model.episodeDetailsResponse.EpisodeDetailsResponse
import com.martianmode.studycase.data.model.locationDetailsResponse.LocationDetailsResponse
import com.martianmode.studycase.data.model.locationsResponse.LocationsResponse
import com.martianmode.studycase.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val apiRepository: ApiRepository) :
    ViewModel() {

    var locationsResponse by mutableStateOf<LocationsResponse?>(null)
    var locationDetailsResponse by mutableStateOf<LocationDetailsResponse?>(null)
    var locationCharactersResponse = mutableStateListOf<CharacterDetailsResponse?>(null)

    fun getLocations() {
        viewModelScope.launch {
            apiRepository.getLocations()?.let { response ->
                withContext(Dispatchers.Main) {
                    locationsResponse = response
                }
            }
        }
    }

    fun getLocationDetails(id: Int?) {
        id?.let {
            viewModelScope.launch {
                runCatching {
                    val response = apiRepository.getLocationDetails(id)
                    val characterIds = response?.residents?.take(20)?.map { it.getCharacterIdFromPath() }
                        ?.joinToString(separator = ",") { it }
                    val characters = if(characterIds?.isNotEmpty() == true) apiRepository.getMultipleCharacters(characterIds) else mutableStateListOf()
                    withContext(Dispatchers.Main) {
                        locationDetailsResponse = response
                        locationCharactersResponse = characters?.toMutableStateList() ?: mutableStateListOf()
                    }
                }.onFailure {
                    Log.d("LocationsViewModel",it.message.toString())
                }
            }
        }
    }
}