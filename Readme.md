# Polaris

This folder is primarily a container for the top-level pieces of the application.

## Pre Requirement

 - JDK 8.x
 - Maven 3.2 or above
 - ExtJS 6.0.1(Included in this project)
 - Sencha Cmd 6.x

## Basic Application Structure

Applications that target a single toolkit will have the following structure.

    main/                # Contains Source code
        extapp/          # ExtJS based web application
        java/           # Server-sided web application
        resources/          # Configurations

    test/          # Test code
    webapp       # Web Meta information

## Build & Running

If you want to build polaris from the source, please first clone this repository. And then:

To create excutable jar,

```
mvn clean package spring-boot:repackage
```

and you need to run application using java command.

```
java -jar target/polaris-1.0-SNAPSHOT.jar
```

To run include build process,

 ```
 mvn clean spring-boot:run
 ```

## Browse Web Application

```
http://localhost:8180/app/login
```
