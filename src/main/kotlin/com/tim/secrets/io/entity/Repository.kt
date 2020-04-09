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
open class Repository(
        @field:PersistenceContext @param:CurrentSession private val entityManager: EntityManager
) {
    @Transactional(readOnly = true)
    open fun findById(id: @NotNull Long?): Option<GreatEntity> {
        return Option.fromNullable(entityManager.find(GreatEntity::class.java, id))
    }

    @Transactional
    open fun save(name: @NotBlank String?): GreatEntity {
        val genre = GreatEntity(name)
        entityManager.persist(genre)
        return genre
    }

}
