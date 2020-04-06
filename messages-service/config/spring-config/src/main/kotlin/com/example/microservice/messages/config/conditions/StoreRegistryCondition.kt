package com.example.microservice.messages.config.conditions

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Profiles
import org.springframework.core.type.AnnotatedTypeMetadata

class StoreRegistryCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val attrs = metadata.getAllAnnotationAttributes(Profile::class.java.name)
        if (attrs != null) {
            for (value in attrs["value"]!!) {
                if (context.environment.acceptsProfiles(Profiles.of(*value as Array<String?>))) {
                    return true
                }
            }
            return false
        }
        return true
    }
}
