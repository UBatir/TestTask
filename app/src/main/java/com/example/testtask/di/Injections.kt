package com.example.testtask.di

import com.example.testtask.ui.MainAdapter
import com.google.gson.GsonBuilder
import org.koin.dsl.module


val remoteModule = module {
    single {
        GsonBuilder().setLenient().create()
    }
}

val adapterModule = module {
    single { MainAdapter() }
}
