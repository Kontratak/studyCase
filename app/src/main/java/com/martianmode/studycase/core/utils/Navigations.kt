package com.martianmode.studycase.core.utils

enum class Navigation(val route : String) {
    CHARACTERS("/characters"),
    LOCATIONS("/locations"),
    EPISODES("/episodes"),
    CHARACTER_DETAILS("/characterDetails"),
    LOCATION_DETAILS("/locationDetails")
}