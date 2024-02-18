package com.example.rick_and_morty_api.ui.activity.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty_api.CartoonModel
import com.example.rick_and_morty_api.CartoonRepository
import com.example.rick_and_morty_api.utils.Resource

class CharacterViewModel(private val repository: CartoonRepository) : ViewModel() {

    private val _characterLv = MutableLiveData<Resource<CartoonModel>>()
    val characterLv: LiveData<Resource<CartoonModel>> = _characterLv


    fun getData(id: Int) {
        _characterLv.value = repository.getCharacter(id).value
    }

    /*fun getCharacter(id: Int): LiveData<CartoonModel> {
        return repository.getCharacter(id)
    }*/

}