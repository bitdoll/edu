# Hanyang Edu


## Pre Requirement

 - JDK 8.x
 - Maven 3.2 or above
 - Eclipse

## Project Description
 - JPA : Spring / BootSpring / Spring Data(JPA) 기반의 Web Server => 서버프로그램만 있음
 - JPA HTML : HTML 기반에 샘플 프로젝트 JQuery 기반
 - Mobile : JPA_HTML를 안드로이드 기반으로 감싸 만든 프로젝트 -> 하이브리드 앱 방식
 - SampleProject : Spring / BootSpring / Spring Data(JPA) / 메모장 html UI 가 들어가 있음.

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