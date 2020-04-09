package com.tim.secrets.web.jpaStuff

import arrow.core.Option
import com.tim.secrets.io.entity.EncryptedSecret
import com.tim.secrets.io.entity.SecretRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller
class JpaController(private val repo: SecretRepository) {

    @Get("/jpa/{id}")
    fun getData(@PathVariable id: Long): Option<EncryptedSecret> {
        return repo.findById(id)
    }

    @Post("/jpa")
    fun insertData(request: SaveRequest): EncryptedSecret {
        return repo.save(key = request.key, value = request.value)
    }
}

data class SaveRequest(val key: String, val value: String)