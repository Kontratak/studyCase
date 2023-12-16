package com.martianmode.studycase.core.utils

import com.martianmode.studycase.R

enum class BottomNavItems(val route: String, val icon: Int) {
    CHARACTERS(Navigation.CHARACTERS.route, R.drawable.characters),
    LOCATIONS(Navigation.LOCATIONS.route, R.drawable.locations),
    EPISODES(Navigation.EPISODES.route, R.drawable.episodes)
}