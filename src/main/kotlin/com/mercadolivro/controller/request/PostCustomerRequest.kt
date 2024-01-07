package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
data class PostCustomerRequest (

    @field:NotEmpty(message = "Nome não pode ser vazio")
    var name: String,

    @field:Email(message = "Email deve ser válido")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informada")
    var password: String,

    var photoUrl: ByteArray
)