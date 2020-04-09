package com.tim.secrets.io.entity

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "greatentity")
class GreatEntity {

    constructor() {}
    constructor(@NotNull name: String?) {
        this.name = name
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    var name: String? = null

}
