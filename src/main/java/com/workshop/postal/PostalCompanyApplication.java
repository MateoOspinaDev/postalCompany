package com.workshop.postal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(
		info = @Info(
				title = "Servicio De Gestion De Mensajeria",
				description = "Esta api es una api realizada como proyecto final para el bootcamp de makaia, busca automatizar y facilitar el proceso de gestion de los procesos que debe realizar una empresa de mensajeria," +
						"Usando las mejores tecnologias, si deseas obtener mas informacion sobre el proyecto puedes visitar el proyecto en github--->  https://github.com/MateoOspinaDev/postalCompany"+
						" O escribiendo a nuestros correos -->(mateoospinadesarrollo@gmail.com o smyardev2@gmail.com)",
				version = "1.0",
				contact = @Contact(name = "Mateo Ospina--Santiago Martinez", email = "smyardev2@gmail.com")



		)
)
@SpringBootApplication
public class PostalCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostalCompanyApplication.class, args);
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("bearer", new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")))
				.addSecurityItem(new SecurityRequirement().addList("bearer"));
	}

}
