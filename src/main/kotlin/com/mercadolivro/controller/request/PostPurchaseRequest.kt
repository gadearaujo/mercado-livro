package com.mercadolivro.controller.request

import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class PostPurchaseRequest (

    @field:NotNull
    @field:Positive
    val customerId: Int,

    @field:NotNull
    val bookIds: Set<Int> // set se importa apenas com valores diferentes
)
