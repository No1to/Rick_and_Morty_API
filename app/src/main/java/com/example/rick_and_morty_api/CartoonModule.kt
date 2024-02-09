package com.example.rick_and_morty_api

import com.example.rick_and_morty_api.ui.activity.character.CharacterViewModel
import com.example.rick_and_morty_api.ui.activity.characters.CharactersViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val cartoonModule = module {

    single {
        provideInterceptor()
    }

    factory {
        provideOkHttpClient(get())
    }

    single {
        provideRetrofit(get())
    }

    single {
        provideCartoonApiService(get())
    }

    single {
        CartoonRepository(get())
    }

    viewModel {
        CharactersViewModel(get())
    }

    viewModel {
        CharacterViewModel(get())
    }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
}

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
    .writeTimeout(10L, TimeUnit.SECONDS)
    .readTimeout(10L, TimeUnit.SECONDS)
    .connectTimeout(10L, TimeUnit.SECONDS)
    .callTimeout(10L, TimeUnit.SECONDS)
    .addInterceptor(interceptor).build()

fun provideInterceptor(): Interceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


fun provideCartoonApiService(retrofit: Retrofit): CartoonApiService {
    return retrofit.create(CartoonApiService::class.java)

}