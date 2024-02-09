package com.example.rick_and_morty_api.ui.activity.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty_api.CartoonModel
import com.example.rick_and_morty_api.CartoonRepository
import com.example.rick_and_morty_api.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: CartoonRepository) :
    ViewModel() {

    fun getCharacters(): LiveData<Resource<List<CartoonModel>>> {
        return repository.getCharacters()
    }
}