package com.example.microservice.messaging.adapters.gateways.data.audit

import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.application.ports.store.MessageAuditor
import org.javers.core.Javers
import org.javers.core.JaversBuilder
import org.javers.core.metamodel.clazz.EntityDefinition
import org.javers.repository.api.JaversRepository
import org.javers.repository.inmemory.InMemoryRepository

class JaversAuditor private constructor(
    private val javers: Javers
) : MessageAuditor {
    companion object {
        private var INSTANCE: Javers? = null

        private fun builder(): JaversBuilder {
            val messageEntityDef = EntityDefinition(MessageDto::class.java, "id")
            return JaversBuilder.javers().registerEntity(messageEntityDef)
        }

        @Suppress("MemberVisibilityCanBePrivate")
        fun instance(): JaversAuditor {
            return instance(InMemoryRepository())
        }

        @Suppress("MemberVisibilityCanBePrivate")
        fun instance(repository: JaversRepository): JaversAuditor {
            val instance: Javers = INSTANCE.let {
                val messageEntityDef = EntityDefinition(MessageDto::class.java, "id")
                JaversBuilder.javers().registerEntity(messageEntityDef).registerJaversRepository(repository).build()
            }

            return JaversAuditor(instance)
        }
    }

    override fun recordChange(author: String, new: MessageDto, old: MessageDto) {
        this.recordChange(author, new)
    }

    override fun recordChange(author: String, new: MessageDto) {
        this.javers.commit(author, new)
    }
}
