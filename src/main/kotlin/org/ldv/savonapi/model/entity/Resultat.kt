package org.ldv.savonapi.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Resultat (
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    var score: Float,
    var mention: String,
    @ManyToOne
    @JoinColumn(name = "savon_id")
    val savon: Savon,

    @ManyToOne
    @JoinColumn(name = "caracteristque_id")
    val caracteristique: Caracteristique
){
}