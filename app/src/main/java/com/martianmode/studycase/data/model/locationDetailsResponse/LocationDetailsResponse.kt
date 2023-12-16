package com.martianmode.studycase.data.model.locationDetailsResponse

import com.google.gson.annotations.SerializedName


data class LocationDetailsResponse (
    val id : Int? = null,
    val name : String? = null,
    val type : String? = null,
    val dimension : String? = null,
    val residents : ArrayList<Any?> = arrayListOf(),
    val url: String? = null,
    val created : String? = null
)