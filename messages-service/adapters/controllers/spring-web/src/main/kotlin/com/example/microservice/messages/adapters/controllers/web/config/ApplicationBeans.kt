package com.example.microservice.messages.adapters.controllers.web.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(BeansConfig::class)
class ApplicationBeans
