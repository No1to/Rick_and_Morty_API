package com.example.rick_and_morty_api

import androidx.lifecycle.LiveData
import com.example.rick_and_morty_api.utils.Resource

class CartoonRepository(private val api: CartoonApiService): BaseRepository() {

    fun getCharacters(): LiveData<Resource<CharacterResponse>> = doRequest {
        api.getCharacters()
    }

    fun getCharacter(id: Int): LiveData<Resource<CartoonModel>> = doRequest {
        api.getCharacter(id)
    }

}
    /*fun getCharacter(id: Int): LiveData<Resource<CartoonModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = api.getCharacter(id)
                if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                    response.body()?.let {
                        emit(Resource.SuccLoading(it))
                    }
                }
            } catch (io: IOError) {
                emit(Resource.Error(io.message ?: "Error!"))
            }
        }
    }*/

    /*fun getCharacters(): MutableLiveData<Resource<List<CartoonModel>>> {
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
    }*/