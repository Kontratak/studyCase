package com.martianmode.studycase.data.model.locationsResponse

import com.martianmode.studycase.data.model.locationDetailsResponse.LocationDetailsResponse

data class LocationsResponse(
    val info: Info?,
    val results: List<LocationDetailsResponse?>?
)