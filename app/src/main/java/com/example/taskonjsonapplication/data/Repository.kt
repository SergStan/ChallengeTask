package com.example.taskonjsonapplication.data

interface Repository {
    suspend fun loadSeries(): SeriesResult
    suspend fun loadEpisode(seasonNumber: String, episodeNumber: String): EpisodeResult
}