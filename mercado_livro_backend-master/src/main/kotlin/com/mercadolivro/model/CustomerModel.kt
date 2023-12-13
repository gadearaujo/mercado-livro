package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
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
    var password: String

)



