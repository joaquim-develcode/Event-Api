package com.example.events.data

import com.example.events.model.Event
import com.example.events.model.User
import com.example.events.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun checkIn(user: User): Result<User> {
        try {
            ApiClient.create().checkin(user)
            return Result.Success(user)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun events(): Call<List<Event>> {
        return ApiClient.create().events()
    }


    fun logout() {
    }
}