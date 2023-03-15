package com.example.taskonjsonapplication

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val seriesModule = module {

    single<Context> { androidApplication() }
    single<CoroutineDispatcher> { Dispatchers.IO }
}