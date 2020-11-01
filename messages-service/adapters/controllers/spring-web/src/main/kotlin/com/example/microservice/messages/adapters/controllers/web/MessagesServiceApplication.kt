package com.example.microservice.messages.adapters.controllers.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MessagesServiceApplication

fun main(args: Array<String>) {
    val app = SpringApplication.run(arrayOf(MessagesServiceApplication::class.java), args)
    val test = 2
}
