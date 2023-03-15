package com.example.taskonjsonapplication.data

import kotlinx.serialization.Serializable

@Serializable
class SeriesResponse {
    var seasons: ArrayList<Season>? = null
    var title: String? = null

    @Serializable
    class Season {
        var episodes: List<Episode>? = null
    }
}