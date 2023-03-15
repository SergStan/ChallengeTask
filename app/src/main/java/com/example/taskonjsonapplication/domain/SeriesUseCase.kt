package com.example.taskonjsonapplication.domain

import com.example.taskonjsonapplication.data.Repository
import com.example.taskonjsonapplication.data.SeriesResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeriesUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): SeriesResult {
        return withContext(coroutineDispatcher) {
            repository.loadSeries()
        }
    }
}