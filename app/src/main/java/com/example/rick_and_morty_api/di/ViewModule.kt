package com.example.rick_and_morty_api.di

import com.example.rick_and_morty_api.ui.activity.character.CharacterViewModel
import com.example.rick_and_morty_api.ui.activity.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharactersViewModel(get())
    }

    viewModel {
        CharacterViewModel(get())
    }
}
