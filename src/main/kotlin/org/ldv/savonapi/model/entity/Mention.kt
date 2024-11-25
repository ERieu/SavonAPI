package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity

class Mention(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var note : Int,
    @OneToMany(mappedBy = "mention")
    var caracteristiques: List<Caracteristique>? = null
) {
}