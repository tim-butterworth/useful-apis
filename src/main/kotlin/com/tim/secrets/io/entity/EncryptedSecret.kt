package com.tim.secrets.io.entity

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "secrets")
class EncryptedSecret {

    constructor() {}
    constructor(
            key: String,
            value: String
    ) {
        this.key = key
        this.value = value
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotNull
    @Column(name = "key", nullable = false, unique = true)
    var key: String? = null

    @NotNull
    @Column(name = "value", nullable = false, unique = true)
    var value: String? = null

}
