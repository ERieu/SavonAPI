package org.ldv.savonapi.service

import org.ldv.savonapi.model.dao.IngredientDAO
import org.ldv.savonapi.model.entity.Ingredient
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataInitializer (val ingredientDAO: IngredientDAO, val caracteristiqueDAO: CaracteristiqueDAO) : CommandLineRunner {
    override fun run(vararg args: String?) {
        //Pour importer les ingredients 
        if (ingredientDAO.count() == 0L) { // Éviter les doublons
            var huileOlive=  Ingredient(1,"Huile d'olive",189, 78, 111, 9.26f,10.192f,9.838f,9.152f,10.144f,9.298f,10.194f)
            var ingredients= listOf<Ingredient>(huileOlive)
            ingredientDAO.saveAll(ingredients)
        }
    }
}
