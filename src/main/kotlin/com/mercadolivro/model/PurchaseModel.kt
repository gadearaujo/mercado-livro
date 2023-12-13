package com.mercadolivro.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "purchase")
data class PurchaseModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerar automatico no bd
    var id: Int? = null,

    @ManyToOne // muitas comprar por um cliente
    @JoinColumn(name = "customer_id")
    val customer: CustomerModel,

    @ManyToMany // uma compra pode ter varios livros e esse livro pode estar em varias compras
    @JoinTable(name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchase_id")], // qual eh a coluna que referencia a essa tabela de compras
        inverseJoinColumns = [JoinColumn(name = "book_id")])
    val books: List<BookModel>,

    @Column
    val nfe: String? = null,

    @Column
    val price: BigDecimal,

    @Column(name = "created_at")
    val createdAt: LocalDateTime

)