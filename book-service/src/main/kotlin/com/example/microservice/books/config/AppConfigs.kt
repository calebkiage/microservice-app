package com.example.microservice.books.config

import org.springframework.boot.context.properties.ConfigurationProperties

// Application configuration properties for this service go here
@ConfigurationProperties(prefix = "app")
class AppConfigs