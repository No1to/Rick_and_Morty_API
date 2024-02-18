package com.example.rick_and_morty_api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CartoonModel>
}