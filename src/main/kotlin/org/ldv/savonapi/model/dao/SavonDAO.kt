package org.ldv.savonapi.model.dao

import org.ldv.savonapi.model.entity.Savon
import org.springframework.data.jpa.repository.JpaRepository

interface SavonDAO: JpaRepository<Savon, Long> {
}