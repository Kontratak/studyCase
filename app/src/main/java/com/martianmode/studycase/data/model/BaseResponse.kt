package com.martianmode.studycase.data.model

data class BaseResponse<T>(
    val info : InfoResponse?,
    val results : T?
)

data class InfoResponse(
    val count : Int?,
    val pages : Int?,
    val next : String?,
    val prev : String?
)