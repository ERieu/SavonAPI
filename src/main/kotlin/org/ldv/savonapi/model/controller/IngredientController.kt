package org.ldv.savonapi.model.controller

import org.ldv.savonapi.model.dao.IngredientDAO
import org.ldv.savonapi.model.entity.Ingredient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api-savon/v1/ingredients")
class IngredientController(private val ingredientDAO: IngredientDAO) {

    @GetMapping
    fun getAllIngredients(): List<Ingredient> {
        return ingredientDAO.findAll()
    }

    @GetMapping("/{id}")
    fun getIngredientById(@PathVariable id: Long): ResponseEntity<Ingredient> {
        val ingredient = ingredientDAO.findById(id)
        return if (ingredient.isPresent) {
            ResponseEntity.ok(ingredient.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createIngredient(@RequestBody ingredient: Ingredient): ResponseEntity<Ingredient> {
        val savedIngredient = ingredientDAO.save(ingredient)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIngredient)
    }

    @PutMapping("/{id}")
    fun updateIngredient(@PathVariable id: Long, @RequestBody ingredient: Ingredient): ResponseEntity<Ingredient> {
        return if (ingredientDAO.existsById(id)) {
            ingredient.id =id
            val updateIngredient = ingredientDAO.save(ingredient)
            ResponseEntity.ok(updateIngredient)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteIngredient(@PathVariable id: Long): ResponseEntity<Void> {
        return if (ingredientDAO.existsById(id)) {
            ingredientDAO.deleteById(id)
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}