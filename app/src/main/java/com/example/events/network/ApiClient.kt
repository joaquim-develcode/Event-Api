package com.example.events.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiClient {
    companion object {
        private const val BASE_URL = "http://5f5a8f24d44d640016169133.mockapi.io/api/"
        fun create() : EventApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(EventApi::class.java)

        }
    }
}