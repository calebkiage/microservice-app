package com.example.microservice.notifications.core.domain

class NotificationSource(
    val enabled: Boolean,
    val id: String,
    var notification: Notification,
)
