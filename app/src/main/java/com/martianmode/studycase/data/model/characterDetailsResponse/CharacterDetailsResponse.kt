package com.martianmode.studycase.data.model.characterDetailsResponse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class CharacterDetailsResponse(
    val created: String?,
    val episode: List<String?>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: Location?,
    val name: String?,
    val origin: Origin?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
){
    @Composable
    fun isCharacterAlive() : Color {
       return when (status) {
            "Alive" -> MaterialTheme.colorScheme.secondary
            "Dead" -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.primaryContainer
        }
    }
}