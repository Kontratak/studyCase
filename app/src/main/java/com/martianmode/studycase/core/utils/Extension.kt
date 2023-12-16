package com.martianmode.studycase.core.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.reflect.KClass

@OptIn(ExperimentalContracts::class)
inline fun <T: Any, reified R: T> T.asInstance(): R? {
    return if (this is R)
        this
    else
        null
}

fun Any?.getCharacterIdFromPath() : String{
    if(this !is String) return ""
    return this.split("/").last() ?: ""
}