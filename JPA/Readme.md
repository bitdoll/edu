# JPA

This folder is primarily a container for the top-level pieces of the application.

## Pre Requirement

 - JDK 8.x
 - Maven 3.2 or above

## Basic Application Structure

Applications that target a single toolkit will have the following structure.

    main/                 # Contains Source code
        java/             # Server-sided web application
        resources/        # Configurations

    test/                 # Test code
    webapp                # Web Meta information

## Build & Running

If you want to build JPA from the source, please first clone this repository. And then:

To create excutable jar,

```
mvn clean package -Dmaven.test.skip=true
```

and you need to run application using java command.

```
java -jar target/jpa-1.0-SNAPSHOT.war
```

To run include build process,

 ```
 mvn clean spring-boot:run
 ```

## Browse Web Application

```
Welcome
http://localhost:8080/app/index.html

HAL Browser
http://localhost:8080/api/

Swagger
http://localhost:8080/app/swagger/index.html
=> http://localhost:8080/api-docs
```