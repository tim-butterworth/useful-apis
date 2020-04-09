package com.tim.secrets.web.jpaStuff

import arrow.core.Option
import com.tim.secrets.io.entity.GreatEntity
import com.tim.secrets.io.entity.Repository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller
class JpaController(private val repo: Repository) {

    @Get("/jpa/{id}")
    fun getData(@PathVariable id: Long): Option<GreatEntity> {
        return repo.findById(id)
    }

    @Post("/jpa")
    fun insertData(request: SaveRequest): GreatEntity {
        return repo.save(request.name)
    }
}

data class SaveRequest(val name: String)