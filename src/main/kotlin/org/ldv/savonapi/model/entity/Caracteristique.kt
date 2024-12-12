package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity
class Caracteristique(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id: Long?= null,
    val nom : String,

    @OneToMany(mappedBy = "caracteristique")
    var mentions: MutableList<Mention>? = mutableListOf(),

    @OneToMany(mappedBy = "caracteristique")
    var resultats: MutableList<Resultat> = mutableListOf(),
) {
}