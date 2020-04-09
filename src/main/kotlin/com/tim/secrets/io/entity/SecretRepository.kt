package com.tim.secrets.io.entity

import arrow.core.Option
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import org.hibernate.Session
import javax.inject.Singleton
import javax.persistence.PersistenceContext
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Singleton
open class SecretRepository(
        @field:PersistenceContext @param:CurrentSession private val entityManager: Session
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
        val criteriaBuilder = entityManager.criteriaBuilder
        val query = criteriaBuilder.createQuery(EncryptedSecret::class.java)

        val root = query.from(EncryptedSecret::class.java)
        query.select(root)

        val params = criteriaBuilder.parameter(String::class.java)
        query.select(root).where(criteriaBuilder.equal(root.get<String>("key"), params))

        return Option.fromNullable(entityManager.createQuery(query).setParameter(params, key).resultStream.findFirst().orElse(null))
    }
}
