package com.mercadolivro.enums

enum class Errors (val code: String, val message: String) {

    ML101("ML-101", "Livro [%s] não existe"),
    ML201("ML-201", "Cliente [%s] não existe")
}