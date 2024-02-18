package com.example.rick_and_morty_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rick_and_morty_api.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    protected fun <N> doRequest(
        request: suspend () -> Response<N>
    ): LiveData<Resource<N>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = request.invoke()
            if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                response.body()?.let {
                    emit(Resource.SuccLoading(response.body()!!))
                }
            }
        } catch (io: IOException) {
            emit(Resource.Error(io.message ?: "Unknown Error!"))
        }
    }

}
    /*protected fun <N> doRequest(request: suspend () -> Response<N>): LiveData<Resource<N>> =
        withContext(Dispatchers.IO) {
            liveData {
                emit(Resource.Loading())
                try {
                    val response = request()
                    if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                        emit(Resource.SuccLoading(response.body()!!))
                    } else {
                        emit(Resource.Error("Unsuccessful request: ${response.code()}"))
                    }
                } catch (io: IOException) {
                    emit(Resource.Error(io.message ?: "Error!"))
                }
            }
        }*/



/*
abstract class BaseRepository {

    protected fun <N> doRequest(request: suspend () -> Response<N>): LiveData<Resource<N>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = request()
                if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                    emit(Resource.SuccLoading(response.body()!!))
                }
            } catch (io: IOError) {
                emit(Resource.Error(io.message ?: "Error!"))
            }
        }

}*/