package com.martianmode.studycase.data.model

import retrofit2.Response
import com.martianmode.studycase.core.utils.asInstance

class BaseResult<T>(val response : Response<T>? = null){

    var code : Int? = null
    var message : String? = null
    var result : T? = null

    inline fun <reified S> map(): BaseResult<S> {
        val baseResult = BaseResult<S>()
        baseResult.code = response?.code()
        baseResult.message = response?.message()
        baseResult.result = response?.body()?.asInstance()
        return baseResult
    }
}