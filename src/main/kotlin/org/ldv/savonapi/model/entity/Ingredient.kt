package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var nom: String,
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

    @ManyToMany
    @JoinTable(
        name = "ligne",
        joinColumns = [JoinColumn(name = "idSavon")],
        inverseJoinColumns = [JoinColumn(name = "idIngredient")]
    )
    var Savon: List<Savon>? = null
) {
}