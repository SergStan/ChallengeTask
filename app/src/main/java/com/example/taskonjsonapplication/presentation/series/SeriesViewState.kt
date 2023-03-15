package com.example.taskonjsonapplication.presentation.series

sealed class SeriesViewState {
    object Loading : SeriesViewState()
    data class Content(val episodes: List<SeriesRowState>): SeriesViewState()
    data class Error(val throwable: Throwable): SeriesViewState()
}