package com.example.microservice.core.models

data class PageInfo(val endCursor: String? = null, val hasNextCursor: Boolean = false, val startCursor: String? = null)
