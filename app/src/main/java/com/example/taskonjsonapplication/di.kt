package com.example.taskonjsonapplication

import android.content.Context
import com.example.taskonjsonapplication.data.Repository
import com.example.taskonjsonapplication.data.SeriesRepository
import com.example.taskonjsonapplication.data.SeriesSource
import com.example.taskonjsonapplication.domain.EpisodeUseCase
import com.example.taskonjsonapplication.domain.SeriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val seriesModule = module {
    single<Context> { androidApplication() }
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<SeriesSource> { SeriesSource(get()) }
    single<Repository> { SeriesRepository(get()) }
    single<SeriesUseCase> { SeriesUseCase(get(), get()) }
    single<EpisodeUseCase> { EpisodeUseCase(get(), get()) }
}