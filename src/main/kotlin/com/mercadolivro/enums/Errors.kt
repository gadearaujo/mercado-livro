package com.mercadolivro.enums

enum class Errors (val code: String, val message: String) {

    ML101("ML-101", "Livro [%s] não existe"),
    ML102("ML-102", "Não é possível alterar um livro com status [%s]"),
    ML201("ML-201", "Cliente [%s] não existe"),
    ML202("ML-202", "Email [%s] já está sendo usado. Tente outro."),
    ML203("ML-203", "Email ou senha equivocado. Tente novamente."),

}