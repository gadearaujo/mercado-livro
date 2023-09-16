package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PostBookRequest (

    // NotEmpty é mais para String
    @field:NotEmpty(message = "Email deve ser válido")
    var name: String,

    @field:NotNull(message = "Email deve ser válido")
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
)