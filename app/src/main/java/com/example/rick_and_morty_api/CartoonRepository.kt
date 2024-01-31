package com.example.rick_and_morty_api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CartoonRepository @Inject constructor(private val api: CartoonApiService) {

    fun getCharacters(): MutableLiveData<List<CartoonCharacter>> {

        val characters = MutableLiveData<List<CartoonCharacter>>()
        api.getCharacters().enqueue(object : Callback<BaseResponse<CartoonCharacter>> {
            override fun onResponse(
                call: Call<BaseResponse<CartoonCharacter>>,
                response: Response<BaseResponse<CartoonCharacter>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        characters.postValue(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<CartoonCharacter>>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }

        })
        return characters
    }

    fun getCharacter(id: Int): LiveData<CartoonCharacter> {
        val characterLv = MutableLiveData<CartoonCharacter>()
        api.getCharacter(id).enqueue(object : Callback<CartoonCharacter> {

            override fun onResponse(
                call: Call<CartoonCharacter>,
                response: Response<CartoonCharacter>
            ) {
                response.body().let {
                    characterLv.postValue(it)
                }
            }

            override fun onFailure(call: Call<CartoonCharacter>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }
        })
        return characterLv
    }

}