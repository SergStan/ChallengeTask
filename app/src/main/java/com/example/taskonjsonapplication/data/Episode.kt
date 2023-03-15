package com.example.taskonjsonapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    @SerialName("Plot")
    var plot: String? = null,

    @SerialName("Rated")
    var rated: String? = null,

    @SerialName("Title")
    var title: String? = null,

    @SerialName("Ratings")
    var ratings: ArrayList<Rating>? = null,

    @SerialName("Writer")
    var writer: String? = null,

    @SerialName("Actors")
    var actors: String? = null,

    @SerialName("Type")
    var type: String? = null,
    @SerialName("imdbVotes")
    var imdbVotes: String? = null,

    @SerialName("seriesID")
    var seriesID: String? = null,

    @SerialName("Season")
    var season: String? = null,

    @SerialName("Director")
    var director: String? = null,

    @SerialName("Released")
    var released: String? = null,

    @SerialName("Awards")
    var awards: String? = null,

    @SerialName("Genre")
    var genre: String? = null,

    var imdbRating: String? = null,

    @SerialName("Poster")
    var poster: String? = null,

    @SerialName("Episode")
    var episode: String? = null,

    @SerialName("Language")
    var language: String? = null,

    @SerialName("Country")
    var country: String? = null,

    @SerialName("Runtime")
    var runtime: String? = null,

    @SerialName("imdbID")
    var imdbID: String? = null,

    @SerialName("Metascore")
    var metascore: String? = null,

    @SerialName("Response")
    var response: String? = null,

    @SerialName("Year")
    var year: String? = null
)

@Serializable
data class Rating(
    @SerialName("Source")
    var source: String? = null,

    @SerialName("Value")
    var value: String? = null
)


