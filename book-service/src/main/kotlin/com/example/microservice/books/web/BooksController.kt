package com.example.microservice.books.web

import com.example.microservice.books.config.AppConfigs
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/books")
class BooksController(private val config: AppConfigs)
