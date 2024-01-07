package com.mercadolivro.config

import org.springframework.context.annotation.Bean
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc

@EnableSwagger2WebMvc
class SwaggerConfig {
    @Bean // utilizado o = para nao precisar abrir { da funcao
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.mercadolivro.controller"))
        .build()

}