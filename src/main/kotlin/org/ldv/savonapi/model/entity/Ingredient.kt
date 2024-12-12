package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var corpsGras: String,
    var sapo: Int,
    var iode: Int,
    var ins: Int,
    var douceur: Float,
    var lavant: Float,
    var volMousse: Float,
    var tenueMousse: Float,
    var durete: Float,
    var solubilite: Float,
    var sechage: Float,

    @OneToMany(mappedBy = "ingredient")
    var Savon: MutableList<Savon> = mutableListOf()
) {
}