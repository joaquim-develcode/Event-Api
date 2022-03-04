package com.example.events.data

import com.example.events.model.Event
import com.example.events.model.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun checkin(user:User): Result<User> {
        // handle login
        return dataSource.checkIn(user)
    }

    fun events(): Call<List<Event>> {
        return dataSource.events()
    }

    private fun setLoggedInUser(loggedInUser: User) {
        this.user = loggedInUser
    }
}