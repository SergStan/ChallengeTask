package com.example.taskonjsonapplication.data

sealed class EpisodeResult {
    class Success(val episode: Episode) : EpisodeResult()
    class Error(val throwable: Throwable) : EpisodeResult()
}