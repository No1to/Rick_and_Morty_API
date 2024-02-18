package com.example.rick_and_morty_api.di

import com.example.rick_and_morty_api.CartoonRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        CartoonRepository(get())
    }
}
