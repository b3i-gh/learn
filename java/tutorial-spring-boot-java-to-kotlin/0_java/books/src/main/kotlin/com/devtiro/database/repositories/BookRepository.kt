package com.devtiro.database.repositories

import com.devtiro.database.domain.entities.BookEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookEntity?, String?>
