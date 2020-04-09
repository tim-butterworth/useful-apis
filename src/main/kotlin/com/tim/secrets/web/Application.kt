package com.tim.secrets.web

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages(
                        "secrets.web",
                        "secrets.io"
                )
                .mainClass(Application.javaClass)
                .start()
    }
}