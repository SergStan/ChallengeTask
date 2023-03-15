package com.example.taskonjsonapplication.presentation.episode

sealed class EpisodeViewState {
    object Loading : EpisodeViewState()
    data class Content(val episodeState: EpisodeState): EpisodeViewState()
    data class Error(val throwable: Throwable): EpisodeViewState()
}