package com.example.microservice.messages.models

data class Message(val content: String, val requestId: String, val id: Long? = null, val threadId: Long? = null)
