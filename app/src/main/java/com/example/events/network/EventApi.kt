package com.example.events.network

import com.example.events.model.Event
import com.example.events.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {

    @POST("checkin")
    fun checkin(@Body user: User): Call<User>

    @GET("events")
    fun events(): Call<List<Event>>
}