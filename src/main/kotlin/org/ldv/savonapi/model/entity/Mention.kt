package org.ldv.savonapi.model.entity

import jakarta.persistence.*

@Entity

class Mention(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id: Long,
    var nom: String,
    var noteMax: Float,
    var noteMin: Float,

    @ManyToOne
    @JoinColumn(name = "caracteristique_id")
    var caracteristique: Caracteristique? = null
) {
}