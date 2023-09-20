package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import java.awt.print.Book

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO, photoUrl = this.photoUrl, password = this.password)
}


fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id= previousValue.id, name= this.name, email= this.email, status =  previousValue.status, photoUrl = this.photoUrl, password = this.password)
}

fun PostBookRequest.toBookModel(customer: CustomerModel) : BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer,
        photoUrl = ""
        )
}


fun PutBookRequest.toBookModel(previusValue: BookModel) : BookModel {
    return BookModel(
        id = previusValue.id,
        name = this.name ?: previusValue.name ,
        price = this.price ?: previusValue.price,
        status = previusValue.status,
        customer = previusValue.customer,
        photoUrl = previusValue.photoUrl
    )
}


fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status,
        photoUrl = this.photoUrl,
        password = this.password
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        photoUrl = this.photoUrl,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}