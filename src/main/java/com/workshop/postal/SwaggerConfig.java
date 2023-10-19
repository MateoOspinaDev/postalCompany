package com.workshop.postal;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
    @EnableSwagger2
    public class SwaggerConfig {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2).select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo());
        }

        private ApiInfo apiInfo() {
            Contact[] contacts = new Contact[] {
                    new Contact("Santiago Martinez", "https://linkedin.com/in/santiago-martinez-238a6525a", "smyardev2@gmail.com"),
                    new Contact("Mateo Ospina", "https://www.linkedin.com", "mateoospinadesarrollo@gmail.com")
            };

            return new ApiInfoBuilder().title("API para gestión de Sistema de Mensajeria")
                    .description("Esta api es una api realizada como proyecto final para el bootcamp de makaia, busca automatizar y facilitar el proceso de gestion de los procesos que debe realizar una empresa de mensajeria," +
                                    "Usando las mejores tecnologias, si deseas obtener mas informacion sobre el proyecto puedes visitar el proyecto en github--->  https://github.com/MateoOspinaDev/postalCompany")

                    .version("1.0.0")
                    .contact(contacts[0])
                    .build();
        }

    }

