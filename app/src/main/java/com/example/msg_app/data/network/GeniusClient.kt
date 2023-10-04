package com.example.msg_app.data.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object GeniusClient {
    @Provides
    inline fun <reified T> createGeniusInstance(): T {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(LoggingInterceptor())

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.115:8080/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client.build())
            .build()

        return retrofit.create(T::class.java)
    }
}