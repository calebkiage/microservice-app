package com.example.microservice.notifications.core.domain

import java.time.Instant

class Notification(
    val id: String,
    val createdOn: Instant,
    val readOn: Instant?,
    val sentOn: Instant?,
) {
    var sources: MutableSet<NotificationSource> = mutableSetOf()
        set(value) {
            for (item in value) {
                item.notification = this
            }

            field = value
        }

    var targets: MutableSet<NotificationTarget> = mutableSetOf()
        set(value) {
            for (item in value) {
                item.notification = this
            }

            field = value
        }
}
