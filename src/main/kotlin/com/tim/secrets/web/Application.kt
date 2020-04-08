package com.tim.secrets.web

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("secrets")
                .mainClass(Application.javaClass)
                .start()
    }
}