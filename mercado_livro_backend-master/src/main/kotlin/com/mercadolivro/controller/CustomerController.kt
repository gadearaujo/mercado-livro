package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@RestController
@RequestMapping("customer")
class CustomerController (
    val customerService : CustomerService
) {
    @GetMapping
    fun getAll(@RequestParam name : String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() } // transforma em um lista de CustomerResponse
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request : PostCustomerRequest): CustomerModel {
      return customerService.create(request.toCustomerModel())
    }
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @GetMapping("/login")
    fun loginCustomer(
        @RequestParam email: String,
        @RequestParam password : String,
    ): List<CustomerResponse> {
        return customerService.loginCustomer(email, password).map { it.toResponse() }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer : PutCustomerRequest) {
        val  customerSaved = customerService.findById(id)
         customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }

    @PostMapping(value= ["/{id}/profile-picture"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun setProfilePicture(@PathVariable("id") id: Int, @RequestParam file: MultipartFile): ResponseEntity<Void>{

        println("setting file: $file")
        return try {
            customerService.setProfilePicture(id, file)
            ResponseEntity
                .created(URI("/customer/${id}/profile-picture"))
                .build()
        } catch(error: NoSuchElementException){
            ResponseEntity
                .notFound()
                .build()
        }
    }

    @GetMapping("/{id}/profile-picture")
    fun getProfilePicture(@PathVariable("id") id: Int): ResponseEntity<Any>{
        println("getprofile/id=$id")
        return try {
            val image: ByteArray = customerService.getProfilePicture(id)
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