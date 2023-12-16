package com.martianmode.studycase.presentation.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse
import com.martianmode.studycase.data.model.charactersResponse.CharactersResponse
import com.martianmode.studycase.data.remote.ApiService
import com.martianmode.studycase.data.repository.ApiRepository
import com.martianmode.studycase.di.ApiModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    var charactersResponse by mutableStateOf<CharactersResponse?>(null)
    var characterDetailResponse by mutableStateOf<CharacterDetailsResponse?>(null)

    fun getCharacters(){
        viewModelScope.launch{
            apiRepository.getCharacters()?.let {response ->
                withContext(Dispatchers.Main){
                    charactersResponse = response
                }
            }
        }
    }

    fun getCharacterDetails(id:Int?){
        id?.let {
            viewModelScope.launch{
                apiRepository.getCharacterDetails(id)?.let {response ->
                    withContext(Dispatchers.Main){
                        characterDetailResponse = response
                    }
                }
            }
        }
    }

    fun getCharactersWithApiService(){
        val apiService = ApiModule().provideRetrofit().create(ApiService::class.java)
        val repository = ApiRepository(apiService)
        viewModelScope.launch{
            repository.getCharacters()?.let { response ->
                withContext(Dispatchers.Main){
                    charactersResponse = response
                }
            }
        }
    }

}