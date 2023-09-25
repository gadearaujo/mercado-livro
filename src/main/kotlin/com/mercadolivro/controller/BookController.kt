package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI


@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request : PostBookRequest) : BookModel {
        val customer = customerService.findById(request.customerId)
       return bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse>{
        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 30) pageable: Pageable): Page<BookResponse> =
        bookService.findActives(pageable).map { it.toResponse() }


    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) : BookResponse {
       return bookService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))

    }

    @PostMapping(value= ["/{id}/book-picture"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun setBookPicture(@PathVariable("id") id: Int, @RequestParam file: MultipartFile): ResponseEntity<Void> {

        return try {
            bookService.setBookPicture(id, file)
            ResponseEntity
                .created(URI("/book/${id}/book-picture"))
                .build()
        } catch(error: NoSuchElementException){
            ResponseEntity
                .notFound()
                .build()
        }
    }

    @GetMapping("/{id}/book-picture")
    fun getBookPicture(@PathVariable("id") id: Int): ResponseEntity<Any> {

        return try {
            val image: ByteArray = bookService.getProfilePicture(id)
            println(image)
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${System.currentTimeMillis()}\"")
                .body(image)



        } catch(error: NoSuchElementException){
            ResponseEntity
                .notFound()
                .build()
        }

    }

}