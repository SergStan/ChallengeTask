package com.example.taskonjsonapplication.presentation.episode

data class EpisodeState(
    val seasonNumber: String,
    val episodeName: String,
    val episodeNumber: String,
    val episodeDescription: String,
    val episodeIcon: String?
)