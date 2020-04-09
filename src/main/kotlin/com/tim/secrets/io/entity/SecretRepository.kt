package com.tim.secrets.io.entity

import arrow.core.Option
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Singleton
open class SecretRepository(
        @field:PersistenceContext @param:CurrentSession private val entityManager: EntityManager
) {
    @Transactional(readOnly = true)
    open fun findById(id: @NotNull Long?): Option<EncryptedSecret> {
        return Option.fromNullable(entityManager.find(EncryptedSecret::class.java, id))
    }

    @Transactional
    open fun save(key: @NotBlank String, value: @NotBlank String): EncryptedSecret {
        val secret = EncryptedSecret(
                key = key,
                value = value
        )
        entityManager.persist(secret)

        return secret
    }

    @Transactional
    open fun findByKey(key: String): Option<EncryptedSecret> {
        return Option.fromNullable(entityManager.find(EncryptedSecret::class.java, "key"))
    }
}
