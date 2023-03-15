package com.example.taskonjsonapplication.data

sealed class SeriesResult {
    class Success(val episodes: List<Episode>) : SeriesResult()
    class Error(val throwable: Throwable) : SeriesResult()
}