package com.example.microservice.messages.data.entities

import java.time.Instant

class Thread(val id: Long, val content: String, val createdOn: Instant, val updatedOn: Instant)
