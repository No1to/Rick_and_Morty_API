package com.example.rick_and_morty_api.ui.activity.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty_api.CartoonRepository
import com.example.rick_and_morty_api.CharacterResponse
import com.example.rick_and_morty_api.utils.Resource

class CharactersViewModel(private val repository: CartoonRepository) : ViewModel() {

    /*
    class CharactersViewModel() : ViewModel() {
        private val repository: CartoonRepository by inject(CartoonRepository::class.java)
        или

    }
    */

    private val _charactersLv = MutableLiveData<Resource<CharacterResponse>>()
    val charactersLv: LiveData<Resource<CharacterResponse>> = _charactersLv

    fun getCharacters()/*: LiveData<Resource<List<CartoonModel>>>*/ {
        /*_charactersLv.value = Resource.Loading()
        repository.getCharacters().observeForever {
            _charactersLv.value = it
        }*/
        _charactersLv.value = Resource.Loading()
        repository.getCharacters().observeForever { resource ->
            _charactersLv.value = resource
        }
//            _charactersLv.value = repository.getCharacters().value
    }

    /*fun getCharacters(): LiveData<Resource<List<CartoonModel>>> {
        return repository.getCharacters()
    }*/
}