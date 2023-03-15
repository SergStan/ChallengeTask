package com.example.taskonjsonapplication.presentation.series

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskonjsonapplication.R
import com.example.taskonjsonapplication.STRING_HOLDER
import com.example.taskonjsonapplication.data.SeriesResult
import com.example.taskonjsonapplication.domain.SeriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SeriesViewModel(
    application: Application,
    private val episodesUseCase: SeriesUseCase
) : AndroidViewModel(application) {

    private val _results = MutableLiveData<SeriesViewState>()
    val result: LiveData<SeriesViewState> = _results

    fun loadSeries() {
        viewModelScope.launch(Dispatchers.Main) {
            _results.value = SeriesViewState.Loading
            _results.value = when (val result = episodesUseCase.invoke()) {
                is SeriesResult.Success -> {
                    val rows = result.episodes.map { episode ->
                        val textSeasonNumber = getApplication<Application>().getString(
                            R.string.template_season,
                            episode.season ?: STRING_HOLDER
                        )
                        val textEpisodeNumber = getApplication<Application>().getString(
                            R.string.template_episode,
                            episode.episode ?: STRING_HOLDER
                        )
                        SeriesRowState(
                            textSeasonNumber,
                            textEpisodeNumber,
                            episode.title ?: STRING_HOLDER,
                            episode.poster
                        )
                    }
                    SeriesViewState.Content(rows)
                }
                is SeriesResult.Error -> {
                    Log.e("TAG", result.throwable.localizedMessage, result.throwable)
                    SeriesViewState.Error(result.throwable)
                }
            }
        }
    }
}