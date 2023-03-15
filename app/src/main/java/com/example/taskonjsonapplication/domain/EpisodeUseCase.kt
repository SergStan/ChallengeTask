package com.example.taskonjsonapplication.domain

import com.example.taskonjsonapplication.data.EpisodeResult
import com.example.taskonjsonapplication.data.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EpisodeUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(season: String, episode: String): EpisodeResult {
        return withContext(coroutineDispatcher) {
            repository.loadEpisode(season, episode)
        }
    }
}