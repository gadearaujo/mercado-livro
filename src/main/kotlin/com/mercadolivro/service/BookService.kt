package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class BookService (
    val bookRepository: BookRepository
){
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    @GetMapping
    fun findAll(): List<BookModel> {
        return bookRepository.findAll().toList()
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        var book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(toBookModel: BookModel) {
        bookRepository.save(toBookModel)
    }
}