package com.example.events.ui.events

import com.example.events.model.Event

/**
 * Authentication result : success (user details) or error message.
 */
data class EventResult(
    val success: List<Event>? = null,
    val error: Int? = null
)