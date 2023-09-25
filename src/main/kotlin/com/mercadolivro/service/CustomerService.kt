package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class CustomerService (
    val customerRepository: CustomerRepository,
    val bookService: BookService
){

    fun getAll(name : String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer : CustomerModel): CustomerModel {
        return customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow {
            NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code)
        }
    }

    fun loginCustomer(email: String, password: String): List<CustomerModel> {

        if(customerRepository.findByEmailAndPassword(email, password).isEmpty()) {
            println(email)
            println(password)
            throw BadRequestException(Errors.ML203.message.format(email), Errors.ML203.code)
        } else {
            return customerRepository.findByEmailAndPassword(email, password).toList()
        }
    }

    fun update(customer: CustomerModel) {
       if (!customerRepository.existsById(customer.id!!)) {
           throw Exception()
       }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {

        return !customerRepository.existsByEmail(email)

    }

    fun setProfilePicture(id: Int, file: MultipartFile) {
        println("file: $file")

        val customerModel : CustomerModel = customerRepository.findById(id).orElseThrow()
        customerModel.photoUrl = file.bytes
        customerRepository.save(customerModel)
    }

    fun getProfilePicture(id: Int): ByteArray{
        val user: CustomerModel = customerRepository.findById(id).orElseThrow()
        return user.photoUrl
    }
}