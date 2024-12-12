package org.ldv.savonapi.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Savon (
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var description: String,
    var avecSoude: Boolean,
    var concentrationAlcalin: Float,
    var titre: String,
    var apportEnEau: Float,
    var surgraissage: Float,
    var qteAlcalin: Float? = null,

    @OneToMany(mappedBy = "savon")
    var lignes: MutableList<Ligne> = mutableListOf(),

    @OneToMany(mappedBy = "savon")
    var resultats: MutableList<Resultat> = mutableListOf(),
) {
    fun calculQteAlcalin() {
        // Étape 1 : Initialiser qteAlcalinNormal à 0
        var qteAlcalinNormal = 0.0f

        // Étape 2 : Calculer qteAlcalinNormal en fonction de l'agent alcalin utilisé
        qteAlcalinNormal = if (avecSoude) {
            // Si on utilise de la soude (NaOH)
            this.lignes.sumOf { it.quantite * (it.ingredient.sapo * (40.0 / 56.0) / 1000.0) }.toFloat()
        } else {
            // Si on utilise de la potasse (KOH)
            this.lignes.sumOf { it.quantite * (it.ingredient.sapo / 1000.0) }.toFloat()
        }

        // Étape 3 : Calculer la quantité d'alcalin après prise en compte de la concentration
        var qteAlcalin = qteAlcalinNormal / (concentrationAlcalin / 100.0f)

        // Étape 4 : Réduire qteAlcalin en fonction du surgraissage
        qteAlcalin -= qteAlcalin * (surgraissage / 100.0f)

        // Étape 5 : Stocker le résultat en tant que Float
        this.qteAlcalin = qteAlcalin.toFloat()
    }

    fun calculApportEau() {
        // Étape 1 : Obtenir la concentration en eau
        val concentrationEau = (100 - concentrationAlcalin) / 100

        // Étape 2 : Calculer l'apport en eau
        val apportEau = qteAlcalin?.times(concentrationEau) ?: 0f

        // Étape 3 : Assigner apportEau à this.apportEnEau
        this.apportEnEau = apportEau
    }

    fun calculScoreNonPondere() {
        val ins = this.lignes.sumOf { it.ingredient!!.ins.toDouble() * (it.pourcentage / 100.0f) }
        val iode = this.lignes.sumOf { it.ingredient!!.iode.toDouble() * (it.pourcentage / 100.0f) }

        this.resultats.find { it.caracteristique!!.nom == "ins" }!!.score = ins.toFloat()
        this.resultats.find { it.caracteristique!!.nom == "iode" }!!.score = iode.toFloat()
    }
}