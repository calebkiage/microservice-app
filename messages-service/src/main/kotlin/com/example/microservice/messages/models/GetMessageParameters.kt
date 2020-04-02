package com.example.microservice.messages.models

data class GetMessageParameters(val from: String?, val to: String?, val first: Int = 10, val after: String?)
