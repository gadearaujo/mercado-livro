package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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
    fun create(@RequestBody request : PostCustomerRequest): CustomerModel {
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

}