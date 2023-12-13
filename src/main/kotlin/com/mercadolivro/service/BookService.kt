package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class BookService (
    val bookRepository: BookRepository
){
    fun create(book: BookModel) : BookModel{
       return bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow {
            NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code) }
    }

    fun delete(id: Int) {
        var book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(toBookModel: BookModel) {
        bookRepository.save(toBookModel)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }

        bookRepository.saveAll(books)
    }

    fun setBookPicture(id: Int, file: MultipartFile) {
        println("file: $file")

        val bookModel : BookModel = bookRepository.findById(id).orElseThrow()
        bookModel.photoUrl = file.bytes
        bookRepository.save(bookModel)
    }

    fun getProfilePicture(id: Int): ByteArray {
        val book: BookModel = bookRepository.findById(id).orElseThrow()
        return book.photoUrl
    }
}