package com.example.rick_and_morty_api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rick_and_morty_api.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CartoonRepository @Inject constructor(private val api: CartoonApiService) {

    fun getCharacters(): MutableLiveData<Resource<List<CartoonModel>>> {
        val characters = MutableLiveData<Resource<List<CartoonModel>>>()
        characters.postValue(Resource.Loading())

        api.getCharacters().enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                    response.body()?.let {
                        characters.postValue(
                            Resource.SuccLoading
                            /***/
                                (it.results)
                        )
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                characters.postValue(
                    Resource.Error(/*t.localizedMessage or ->*/t.message ?: "Unknown error!")
                )
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