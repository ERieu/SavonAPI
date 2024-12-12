package org.ldv.savonapi.model.entity

import jakarta.persistence.*


@Entity

class Ligne(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var quantite: Float,
    var pourcentage : Float,

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    val ingredient: Ingredient,

    @ManyToOne
    @JoinColumn(name = "savon_id")
    val savon: Savon
){
}