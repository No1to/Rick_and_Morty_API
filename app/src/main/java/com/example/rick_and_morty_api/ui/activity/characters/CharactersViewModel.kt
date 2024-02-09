package com.example.rick_and_morty_api.ui.activity.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty_api.CartoonModel
import com.example.rick_and_morty_api.CartoonRepository
import com.example.rick_and_morty_api.utils.Resource

class CharactersViewModel(private val repository: CartoonRepository) : ViewModel() {

/*
class CharactersViewModel() : ViewModel() {
    private val repository: CartoonRepository by inject(CartoonRepository::class.java)
    или

}
*/

    fun getCharacters(): LiveData<Resource<List<CartoonModel>>> {
        return repository.getCharacters()
    }
}