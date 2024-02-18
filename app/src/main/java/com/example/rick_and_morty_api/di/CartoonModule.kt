package com.example.rick_and_morty_api.di

import com.example.rick_and_morty_api.BuildConfig
import com.example.rick_and_morty_api.CartoonApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

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