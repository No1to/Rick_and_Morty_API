package com.example.rick_and_morty_api.ui.activity.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty_api.CartoonModel
import com.example.rick_and_morty_api.CartoonRepository

class CharacterViewModel(private val repository: CartoonRepository) : ViewModel() {

    fun getData(id: Int): LiveData<CartoonModel> {
        return repository.getCharacter(id)
    }
}