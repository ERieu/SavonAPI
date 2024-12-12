package org.ldv.savonapi.model.controller

import org.ldv.savonapi.model.dao.MentionDAO
import org.ldv.savonapi.model.entity.Ingredient
import org.ldv.savonapi.model.entity.Mention
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api-savon/v1/mentions")
class MentionController(private val mentionDAO: MentionDAO) {

    @GetMapping
    fun getAllMention(): List<Mention> {
        return mentionDAO.findAll()
    }

    @GetMapping("/{id}")
    fun getMentionById(@PathVariable id: Long): ResponseEntity<Mention> {
        val mention = mentionDAO.findById(id)
        return if (mention.isPresent) {
            ResponseEntity.ok(mention.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createMention(@RequestBody mention: Mention): ResponseEntity<Mention> {
        val savedMention = mentionDAO.save(mention)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMention)
    }

    @PutMapping("/{id}")
    fun updateMention(@PathVariable id: Long, @RequestBody mention: Mention): ResponseEntity<Mention> {
        return if (mentionDAO.existsById(id)) {
            mention.id = id
            val updateIngredient = mentionDAO.save(mention)
            ResponseEntity.ok(updateIngredient)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMention(@PathVariable id: Long): ResponseEntity<Void> {
        return if (mentionDAO.existsById(id)) {
            mentionDAO.deleteById(id)
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}