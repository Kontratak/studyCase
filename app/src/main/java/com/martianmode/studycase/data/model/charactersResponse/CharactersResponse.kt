package com.martianmode.studycase.data.model.charactersResponse

import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse

data class CharactersResponse(
    val info: Info?,
    val results: List<CharacterDetailsResponse?>?
)