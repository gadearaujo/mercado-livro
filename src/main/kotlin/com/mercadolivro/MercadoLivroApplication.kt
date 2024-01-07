package com.mercadolivro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableAsync
@SpringBootApplication
class MercadoLivroApplication

fun main(args: Array<String>) {
	runApplication<MercadoLivroApplication>(*args)
}