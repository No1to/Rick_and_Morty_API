package com.example.rick_and_morty_api.utils

sealed class Resource<N>(
    val message: String? = null,
    val data: N? = null
) {

    class Loading<N> : Resource<N>()

    class Error<N>(message: String) : Resource<N>(message = message)

    class SuccLoading<N>(data: N) : Resource<N>(data = data)

}