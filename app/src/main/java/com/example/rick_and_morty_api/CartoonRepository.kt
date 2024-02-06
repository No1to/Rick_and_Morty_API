package com.example.rick_and_morty_api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CartoonRepository @Inject constructor(private val api: CartoonApiService) {

    fun getCharacters(): MutableLiveData<List<CartoonModel>> {

        val characters = MutableLiveData<List<CartoonModel>>()
        api.getCharacters().enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        characters.postValue(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("noito", t.message.toString())
            }

        })
        return characters
    }

    fun getCharacter(id: Int): LiveData<CartoonModel> {
        val characterLv = MutableLiveData<CartoonModel>()
        api.getCharacter(id).enqueue(object : Callback<CartoonModel> {

            override fun onResponse(
                call: Call<CartoonModel>,
                response: Response<CartoonModel>
            ) {
                response.body().let {
                    characterLv.postValue(it)
                }
            }

            override fun onFailure(call: Call<CartoonModel>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }
        })
        return characterLv
    }

}