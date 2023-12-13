package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PutCustomerRequest (

    @field:NotEmpty(message = "Nome não pode ser vazio")
    var name: String,

    @field:Email(message = "Email deve ser válido")
    var email: String,

    @field:NotEmpty(message = "Email deve ser válido")
    var password: String,

    var photoUrl: ByteArray
)