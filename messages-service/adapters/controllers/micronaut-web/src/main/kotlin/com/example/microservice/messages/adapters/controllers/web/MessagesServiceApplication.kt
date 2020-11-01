package com.example.microservice.messages.adapters.controllers.web

import io.micronaut.runtime.Micronaut

fun main(args: Array<String>) {
    Micronaut.build()
        .args(*args)
        .packages("com.example.microservice.messages.adapters.controllers.web")
        .start()
}
