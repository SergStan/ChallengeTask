package com.example.taskonjsonapplication.presentation.episode

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskonjsonapplication.R
import com.example.taskonjsonapplication.STRING_HOLDER
import com.example.taskonjsonapplication.data.EpisodeResult
import com.example.taskonjsonapplication.domain.EpisodeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EpisodeViewModel(
    application: Application,
    private val episodeUseCase: EpisodeUseCase
) : AndroidViewModel(application) {

    private val _results = MutableLiveData<EpisodeViewState>()
    val result: LiveData<EpisodeViewState> = _results

    fun loadSeries(seasonNumber: String, episodeNumber: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _results.value = EpisodeViewState.Loading
            _results.value =
                when (val result = episodeUseCase.invoke(seasonNumber, episodeNumber)) {
                    is EpisodeResult.Success -> {
                        val episode = result.episode
                        val textSeasonNumber = getApplication<Application>().getString(
                            R.string.template_season,
                            episode.season ?: STRING_HOLDER
                        )
                        val textEpisodeNumber = getApplication<Application>().getString(
                            R.string.template_episode,
                            episode.episode ?: STRING_HOLDER
                        )
                        val state = EpisodeState(
                            textSeasonNumber,
                            textEpisodeNumber,
                            episode.episode ?: STRING_HOLDER,
                            episode.plot?: STRING_HOLDER,
                            episode.poster
                        )
                        EpisodeViewState.Content(state)
                    }
                    is EpisodeResult.Error -> {
                        Log.e("TAG", result.throwable.localizedMessage, result.throwable)
                        EpisodeViewState.Error(result.throwable)
                    }
                }
        }
    }
}