package com.example.events.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.events.data.LoginRepository
import com.example.events.data.Result

import com.example.events.R
import com.example.events.model.Event
import com.example.events.model.User
import com.example.events.ui.events.EventFormState
import com.example.events.ui.events.EventResult
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    private val _eventForm = MutableLiveData<EventFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    val eventFormState: LiveData<EventFormState> = _eventForm

    private val _loginResult = MutableLiveData<LoginResult>()
    private val _eventResult = MutableLiveData<List<Event>>()
    val loginResult: LiveData<LoginResult> = _loginResult
    val eventResult: LiveData<List<Event>> = _eventResult

    fun events() {
        loginRepository.events()
            .enqueue(object : Callback<List<Event>> {
                override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                    if (response.isSuccessful && response.body() != null) {
                        _eventResult.postValue(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                    t.printStackTrace()
                }


            });
    }

    fun login(username: String, email: String) {
        // can be launched in a separate asynchronous job
        val user = User()
        user.eventId = "1"
        user.name = username
        user.email = email

        val result = loginRepository.checkin(user)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = result.data.name?.let { LoggedInUserView(displayName = it) })
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, email: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.length > 3
    }

    // A placeholder email validation check
    private fun isEmailValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }


}