package com.example.microservice.transactions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactionsServiceApplication

fun main(args: Array<String>) {
  runApplication<TransactionsServiceApplication>(*args)
}