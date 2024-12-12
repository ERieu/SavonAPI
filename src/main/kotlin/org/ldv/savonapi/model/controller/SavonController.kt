package org.ldv.savonapi.model.controller

import org.ldv.savonapi.model.dao.SavonDAO
import org.ldv.savonapi.model.entity.Savon
import org.ldv.savonapi.service.SimulateurService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/api-savon/v1/savon")
class SavonController(
    val simulateurService: SimulateurService,
    val savonDAO: SavonDAO,
) {

    @GetMapping
    fun index(): List<Savon> {
        return savonDAO.findAll()
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): ResponseEntity<Savon> {
        val savon = savonDAO.findById(id)
        return if (savon.isPresent) {
            ResponseEntity.ok(savon.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (savonDAO.existsById(id)) {
            savonDAO.deleteById(id)
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}