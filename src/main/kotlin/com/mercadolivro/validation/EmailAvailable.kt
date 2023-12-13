package com.mercadolivro.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvailableValidator::class]) // quem vai fazer a validação dessa classe
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD) // especificando o que ele é
annotation class EmailAvailable(
    val message: String = "Email já cadastrado",
    val groups: Array<KClass<*>> = [], // padrão,
    val payload: Array<KClass<out Payload>> = []
)
