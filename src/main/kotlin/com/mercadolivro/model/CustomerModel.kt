package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Profile

import jakarta.persistence.*
@Entity(name = "customer")

data class CustomerModel (

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

   @Column
    var name: String,

   @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Lob
    @Column(length = 1000)
    var photoUrl: ByteArray,

    @Column
    var password: String,

   @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name="customer_id")]) // tabela que nao tem id
   @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER) // quando buscar na tabela customer, pegar as roles tb (com o FetchType.EAGER)
   @Column(name = "role")
   @Enumerated(EnumType.STRING)
    var roles: Set<Profile> = setOf() // set vazio

)



