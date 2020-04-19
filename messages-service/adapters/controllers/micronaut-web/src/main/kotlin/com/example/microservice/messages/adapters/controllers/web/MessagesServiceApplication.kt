package com.example.microservice.messages.adapters.controllers.web

import io.micronaut.runtime.Micronaut

object MessagesServiceApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.example.microservice.messages.adapters.controllers.web")
                .mainClass(MessagesServiceApplication.javaClass)
                .start()
    }
}
