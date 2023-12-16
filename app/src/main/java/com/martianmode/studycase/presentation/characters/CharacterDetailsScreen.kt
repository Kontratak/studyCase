package com.martianmode.studycase.presentation.characters

import android.widget.ListView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.martianmode.studycase.R
import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse
import com.martianmode.studycase.presentation.base.BaseScreen
import com.martianmode.studycase.presentation.characters.component.CharacterItem

@Composable
fun CharacterDetailsScreen(navController : NavHostController,id : Int?) {

    val charactersViewModel = hiltViewModel<CharactersViewModel>()

    LaunchedEffect(Unit){
        charactersViewModel.getCharacterDetails(id)
    }

    BaseScreen(navController,title = charactersViewModel.characterDetailResponse?.name,bottomBarEnabled = false) {
        charactersViewModel.characterDetailResponse?.let { model ->
            CharacterDetailsView(model)
        }
    }
}

@Composable
fun CharacterDetailsView(model: CharacterDetailsResponse){
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .clip(RoundedCornerShape(24.dp))
                .aspectRatio(1f)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = model.image,
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Character Image",
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 37.dp)) {
            Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = model.name ?: "", style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
            Row(
                Modifier
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Box(Modifier.width(16.dp).height(16.dp).clip(CircleShape).background(model.isCharacterAlive())) {}
                Spacer(Modifier.width(4.dp))
                Text(textAlign = TextAlign.Center, text = "${model.status} - ${model.species}", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(textAlign = TextAlign.Start,text = "Gender", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            Text(textAlign = TextAlign.End, text = model.gender ?: "Unknown", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(textAlign = TextAlign.Start,text = "Last Known Location", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            Text(textAlign = TextAlign.End, text = model.location?.name ?: "Unknown", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(textAlign = TextAlign.Start,text = "Origin", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            Text(textAlign = TextAlign.End, text = model.origin?.name ?: "Unknown", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(textAlign = TextAlign.Start,text = "Created At", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            Text(textAlign = TextAlign.End, text = model.created ?: "Unknown", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        }
    }
}


@Composable
@Preview
fun CharacterDetailsScreenPreview(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        CharacterDetailsView(CharacterDetailsResponse(
            created = null,
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
        ))
    }

}