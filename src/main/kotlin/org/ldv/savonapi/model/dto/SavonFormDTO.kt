package org.ldv.savonapi.model.dto

class SavonFormDTO(
    var id: Long? = null,
    var titre: String,
    var description: String,
    var surgraissage: Float,
    var avecSoude: Boolean,
    var concentrationAlcalin: Float,
    var ligne: MutableList<LigneDTO> = mutableListOf(),
) {
}