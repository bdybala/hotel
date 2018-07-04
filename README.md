# Hotel Management system

## Technologies
* Java 8
* Spring Boot 1.5.7
* Apache Tomcat 8.5
* Spring 4
* Hibernate 5
* PostgreSQL 9
* Gradle
* Mockito 2

## Setup
Database connection configuration should be defined in 

##### src/main/resources/application.properties
```

spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel
spring.datasource.username=bdyb
spring.datasource.password=bdyb

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

## External API produced