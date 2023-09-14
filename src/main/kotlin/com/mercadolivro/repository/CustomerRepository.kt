package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int>{

    fun findByNameContaining(name: String) : List<CustomerModel>

    fun findByEmailContaining(email: String) : List<CustomerModel>

    fun findByEmailAndPassword(email: String, password: String) :  List<CustomerModel>

}