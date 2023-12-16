package com.martianmode.studycase.presentation.locations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.martianmode.studycase.core.utils.Navigation
import com.martianmode.studycase.presentation.base.BaseScreen
import com.martianmode.studycase.presentation.locations.component.LocationItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationsScreen(navController: NavHostController) {
    val locationsViewModel = hiltViewModel<LocationsViewModel>()

    LaunchedEffect(Unit){
        locationsViewModel.getLocations()
    }


    BaseScreen(navController,"Locations") {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            content = {
                items(locationsViewModel.locationsResponse?.results?.size ?: 0){ index ->
                    LocationItem(model = locationsViewModel.locationsResponse?.results?.get(index)){
                        navController.navigate("${Navigation.LOCATION_DETAILS.route}?id=$it")
                    }
                }
            },
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)
        )
    }
}