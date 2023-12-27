package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class PostPurchaseRequest (

    @field:NotNull
    @field:Positive
    @JsonAlias("customer_id") // utilizando snake_case
    val customerId: Int,
    @field:NotNull
    @JsonAlias("book_ids") // utilizando snake_case
    val bookIds: Set<Int> // set se importa apenas com valores diferentes
)
