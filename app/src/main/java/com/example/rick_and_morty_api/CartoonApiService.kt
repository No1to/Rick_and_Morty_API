package com.example.rick_and_morty_api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    fun getCharacters(): Call<CharacterResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<CartoonModel>
}