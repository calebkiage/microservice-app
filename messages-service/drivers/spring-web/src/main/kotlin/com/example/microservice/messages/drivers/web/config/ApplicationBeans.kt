package com.example.microservice.messages.drivers.web.config

import com.example.microservice.messages.config.BeansConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(BeansConfig::class)
class ApplicationBeans
