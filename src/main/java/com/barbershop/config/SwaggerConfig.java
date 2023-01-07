package com.barbershop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket apiDocket() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.barbershop"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(getApiInfo())
                    ;
        }

        private ApiInfo getApiInfo() {
            return new ApiInfo(
                    "BarberShop App",
                    "Api para el servicio de una barberia donde va poder agregar clientes, agregar turnos y ver sus fechas",
                    "1.0",
                    "http://google.com",
                    new Contact("Elian", "https://google.com", "elianelian5@gmail.com"),
                    "MIT",
                    "https://www.google.com",
                    Collections.emptyList()
            );
        }

    }

