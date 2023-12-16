package com.martianmode.studycase.presentation.characters.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse

@Composable
fun CharacterItem(model : CharacterDetailsResponse?,onCharacterSelected : (id : Int?) -> Unit) {
    Row(
        Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min).clickable { onCharacterSelected(model?.id) }) {
        Box(
            Modifier.clip(CircleShape)
                .aspectRatio(1f)) {
            AsyncImage(
                model = model?.image,
                contentDescription = "Character Image",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(text = model?.name ?: "", style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
            Text(text = "${model?.status} - ${model?.species}", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
        }
    }
}

@Preview
@Composable
fun CharacterItemPreview(){
    Box() {
        CharacterItem(
            model = CharacterDetailsResponse(
                created = "",
                episode = null,
                gender = "Male",
                id = null,
                image = "https://rickandmortyapi.com/api/character/avatar/77.jpeg",
                location = null,
                status = "Alive",
                name = "Cowboy Morty",
                origin = null,
                species = "Human",
                type = null,
                url = "https://rickandmortyapi.com/api/character/77"
            )
        ){

        }
    }
}