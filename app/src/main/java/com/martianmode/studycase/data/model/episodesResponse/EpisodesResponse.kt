package com.martianmode.studycase.data.model.episodesResponse

import com.martianmode.studycase.data.model.episodeDetailsResponse.EpisodeDetailsResponse

data class EpisodesResponse(
    val info: Info?,
    val results: List<EpisodeDetailsResponse?>?
)