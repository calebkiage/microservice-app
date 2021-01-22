package com.example.microservice.notifications.core.domain

class NotificationTarget(
    val enabled: Boolean,
    val id: String,
    var notification: Notification,
)
