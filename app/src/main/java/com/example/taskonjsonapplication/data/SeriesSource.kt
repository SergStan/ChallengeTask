package com.example.taskonjsonapplication.data

import android.content.Context
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream

class SeriesSource (private val context: Context, ) {

    suspend fun loadSeries(): SeriesResponse? {
        return loadFromAsset()?.let {
            Json.decodeFromString(
                SeriesResponse.serializer(),
                it
            )
        }
    }

    private fun loadFromAsset(): String? = try {
        val `is`: InputStream = context.assets.open("hbo_silicon_valley.json")
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}