package com.example.microservice.books

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BookServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(BookServiceApplication::class.java), args)
}
