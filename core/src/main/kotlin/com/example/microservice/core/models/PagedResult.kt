package com.example.microservice.core.models

data class PagedResult<T>(val data: List<T>, val totalCount: Long, val pageInfo: PageInfo)
