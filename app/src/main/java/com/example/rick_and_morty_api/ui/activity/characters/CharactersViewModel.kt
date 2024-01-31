package com.example.rick_and_morty_api.ui.activity.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty_api.CartoonCharacter
import com.example.rick_and_morty_api.CartoonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: CartoonRepository) : ViewModel() {

    fun getCharacters(): LiveData<List<CartoonCharacter>> {
        return repository.getCharacters()
    }
}