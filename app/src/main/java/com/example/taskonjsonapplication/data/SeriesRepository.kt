package com.example.taskonjsonapplication.data


class SeriesRepository(private val source: SeriesSource) : Repository {

    override suspend fun loadSeries(): SeriesResult {
        return try {
            val response = source.loadSeries()
            val episodes = response?.seasons
                ?.map { it.episodes }
                ?.flatMap { listOfEpisodes ->
                    listOfEpisodes
                        ?: emptyList()
                }
            response?.let {
                SeriesResult.Success(episodes ?: emptyList())
            } ?: SeriesResult.Error(Throwable("Can not parse JSON"))

        } catch (throwable: Throwable) {
            SeriesResult.Error(throwable)
        }
    }

    override suspend fun loadEpisode(seasonNumber: String, episodeNumber: String): EpisodeResult {
        return try {
            val response = source.loadSeries()
            val episodeDetails: Episode? = response?.seasons?.first {
                it.episodes?.first()?.season.equals(
                    seasonNumber
                )
            }
                ?.episodes
                ?.first { episode ->
                    episode.episode.equals(episodeNumber)
                }
            response?.let {
                episodeDetails?.let { details ->
                    EpisodeResult.Success(details)
                }
            } ?: EpisodeResult.Error(Throwable("Can not parse JSON"))

        } catch (throwable: Throwable) {
            EpisodeResult.Error(throwable)
        }
    }
}