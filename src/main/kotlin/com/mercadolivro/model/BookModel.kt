package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.BadRequestException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")

data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null,

    @Lob
    @Column(length = 1000)
    var photoUrl: ByteArray,

    ) {

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) { // novo valor do atributo, valor que estamos recebendo para inserir dentro da variavel
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw BadRequestException(Errors.ML102.message.format(field), Errors.ML102.code)
            }
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?,
        photoUrl: ByteArray
    ): this(id, name, price, customer, photoUrl) {
            this.status = status
        }

}



