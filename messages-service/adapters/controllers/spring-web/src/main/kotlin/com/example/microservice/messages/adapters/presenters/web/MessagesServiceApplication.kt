package com.example.microservice.messages.adapters.presenters.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MessagesServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(MessagesServiceApplication::class.java), args)
}