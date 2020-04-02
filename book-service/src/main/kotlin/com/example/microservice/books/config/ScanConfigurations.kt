package com.example.microservice.books.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

// Register AppConfigs as a bean
@Configuration
@EnableConfigurationProperties(AppConfigs::class)
class ScanConfigurations
