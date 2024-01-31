package com.example.rick_and_morty_api.ui.activity.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty_api.CartoonCharacter
import com.example.rick_and_morty_api.CartoonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CartoonRepository) : ViewModel() {

    fun getData(id: Int): LiveData<CartoonCharacter> {
        return repository.getCharacter(id)
    }
}