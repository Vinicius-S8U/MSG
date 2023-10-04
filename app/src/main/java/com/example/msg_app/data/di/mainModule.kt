package com.example.msg_app.data.di

import com.example.msg_app.data.api.TmdbApi
import com.example.msg_app.data.network.LoggingInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object mainModule {

    val client = OkHttpClient().newBuilder().addInterceptor(LoggingInterceptor())

    @Provides
    @Singleton
    fun providesGeniusInstance(): TmdbApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(client.build())
        .build()
        .create(TmdbApi::class.java)

}