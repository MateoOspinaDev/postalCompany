# API de Gestión de Mensajería con  (Java & Spring Boot)
## Descripcion:
El proyecto es una REST API que tiene como objetivo automatizar los procesos de envios de mercancia, que permita rastrear, actualizar y tener acceso a la diferente informacion que se genera en una empresa de mensajeria, su fin es facilitar el proceso de envios para las empresas mediante el uso de este desarrollo.

#### El sistema permite:
- Realizar operaciones de envío, seguimiento y gestión de paquetes, así como la actualización de estados de envío por parte de empleados autorizados.
- Crear clientes, actualizarlos, eliminarlos(por cedula) y obtenerlos(por cedula).
- Crear empleados, actualizarlos, eliminarlos(por cedula) y obtenerlos(por cedula).
- Creacion de paquetes, con toda su informacion , asignacion de peso, calculo del valor del envio, seguimiento del estado del paquete.
- Creacion de envios con toda su informacion y acceso a los datos de los actores del envio como son paquete, empleado y cliente.
- Filtracion de Envios por estado.

## Tecnologías Utilizadas:


![Java 17](https://img.shields.io/badge/Java-17-red?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.3-green?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql)
![Gradle](https://img.shields.io/badge/Gradle-Build%20Tool-green?style=for-the-badge&logo=gradle)
![JUnit 5](https://img.shields.io/badge/JUnit%205-Testing-brightgreen?style=for-the-badge&logo=junit)
![Mockito](https://img.shields.io/badge/Mockito-Testing-orange?style=for-the-badge&logo=mockito)
![JWT](https://img.shields.io/badge/JWT-Authentication%20Token-yellow?style=for-the-badge&logo=jwt)
![JPA](https://img.shields.io/badge/JPA-Persistence%20API-blueviolet?style=for-the-badge&logo=jpa)

- **Java 17**: La aplicación está desarrollada en Java 17 como base del proyecto.

- **Spring Boot**: Utilizamos como framework Spring Boot para acelerar el desarrollo y simplificar el desarrollo

- **MySQL**: La base de datos de este proyecto utiliza MySQL para almacenar información sobre clientes, empleados, paquetes y envíos.

- **Gradle**: Gradle se usa como herramienta de construccion y gestor de dependencias

- **JUnit 5 y Mockito**: Hemos implementado pruebas unitarias para garantizar la robustez y confiabilidad de nuestro sistema utilizando JUnit 5 y Mockito.

- **JWT (JSON Web Tokens)**: Para la autenticación y autorización de usuarios, utilizamos JWT como un método seguro para generar y verificar tokens de acceso.

- **JPA (Java Persistence API)**: JPA se utiliza para interactuar con la base de datos de manera eficiente.

- **Postman**: Postman se utiliza para poder testear el API REST

# Instalación y Uso


1. Clonar el repositorio: `git clone https://github.com/MateoOspinaDev/postalCompany`
2. Abra el proyecto de gestión de mensajería con su IDE preferido(Recomendado IntelliJ)
3. Configure el archivo application.properties en la ruta src/main/resources para conectarse a la base de datos Mysql, puede dejar la configuracion por defecto y configurarla a su necesidad(Contraseña, usuario y direccion a su BD)
4. Ejecute el archivo build.gradle para descargar las dependencias del proyecto, cargar las configuraciones y que empiece a compilarlo.
5. Ejecute el archivo PostalCompanyApplication para correr el programa.
6. Acceda a testear y usar la aplicacion desde Postman usando los endpoints que se pueden ver en los controllers
7. Use la aplicacion desde swagger,(poner link swagger)

# Diagramas
![Diagrama de clases](URL de la Imagen)



