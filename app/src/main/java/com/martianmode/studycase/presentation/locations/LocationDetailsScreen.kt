package com.martianmode.studycase.presentation.locations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.martianmode.studycase.R
import com.martianmode.studycase.data.model.characterDetailsResponse.CharacterDetailsResponse
import com.martianmode.studycase.data.model.locationDetailsResponse.LocationDetailsResponse
import com.martianmode.studycase.presentation.base.BaseScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LocationDetailsScreen(navController:NavHostController,id : Int?) {

    val locationsViewModel = hiltViewModel<LocationsViewModel>()

    LaunchedEffect(Unit){
        locationsViewModel.getLocationDetails(id)
    }

    BaseScreen(navController,title = locationsViewModel.locationDetailsResponse?.name,bottomBarEnabled = false) {
        locationsViewModel.locationDetailsResponse?.let { model ->
            LocationDetailsView(model,locationsViewModel.locationCharactersResponse)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationDetailsView(
    model: LocationDetailsResponse,
    locationCharacters: List<CharacterDetailsResponse?>
){
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = -0.4f
    ) {
        locationCharacters.size
    }
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)) {
        if(locationCharacters.size > 1){
            HorizontalPager(state = pagerState) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .aspectRatio(1f)) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = locationCharacters[it]?.image,
                        error = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Character Image",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 37.dp)) {
            if(locationCharacters.size > 1){
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)) {
                    Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = locationCharacters[pagerState.currentPage]?.name ?: "", style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
                    Row(
                        Modifier
                            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Box(
                            Modifier
                                .width(16.dp)
                                .height(16.dp)
                                .clip(CircleShape)
                                .background(locationCharacters[pagerState.currentPage]?.isCharacterAlive() ?: MaterialTheme.colorScheme.primaryContainer)) {}
                        Spacer(Modifier.width(4.dp))
                        Text(textAlign = TextAlign.Center, text = "${locationCharacters[pagerState.currentPage]?.status} - ${locationCharacters[pagerState.currentPage]?.species}", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
                    }
                    Spacer(Modifier.height(16.dp))
                    Row(Modifier.fillMaxWidth()) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .width(48.dp)
                                .height(48.dp)
                                .background(MaterialTheme.colorScheme.onSecondary)
                                .clickable {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        pagerState.scrollToPage(if (pagerState.currentPage >= 1) pagerState.currentPage - 1 else 0)
                                    }
                                }) {
                            Image(modifier = Modifier.align(Alignment.Center), painter = painterResource(id = R.drawable.back), contentDescription = "Back")
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .weight(1f)
                                .height(48.dp)
                                .background(MaterialTheme.colorScheme.secondary)
                                .clickable {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        pagerState.scrollToPage(if (pagerState.currentPage < locationCharacters.size - 1) pagerState.currentPage + 1 else locationCharacters.size - 1)
                                    }
                                }) {
                            Row(Modifier.align(Alignment.Center)) {
                                Text(textAlign = TextAlign.Center, text = "Next", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
                                Icon(modifier = Modifier.rotate(180f),painter = painterResource(id = R.drawable.back), contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                            }
                        }
                    }
                }
            }
            Text(modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center, text = model.dimension ?: "", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = "${model.name} - ${model.type}", style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        }
    }
}
