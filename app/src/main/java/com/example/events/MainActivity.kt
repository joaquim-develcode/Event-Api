package com.example.events

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.events.databinding.ActivityMainBinding
import com.example.events.model.Event
import com.example.events.ui.events.EventsAdapter
import com.example.events.ui.login.LoginViewModel
import com.example.events.ui.login.LoginViewModelFactory

/**
 * @author Joaquim
 */
class MainActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var listEvents: List<Event>
    private var recyclerView: RecyclerView? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        getEvents()
        loginViewModel.eventFormState.observe(this@MainActivity, Observer {
            val eventFormState = it ?: return@Observer
            if (eventFormState.eventError != null) {
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getEvents() {
        loginViewModel.events()
        loginViewModel.eventResult.observe(this@MainActivity, Observer {
            val eventResult = it ?: return@Observer
            listEvents = eventResult
            setDataToAdapter()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setDataToAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        val adapter = EventsAdapter(listEvents)
        recyclerView = binding.mainRecyclerView
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}