# Quarkus Demo 

This project contains:

* CRUD com Testes
* Hibernate Panache
* Hibernate Validator
* Swagger
* Liquibase Migrations

## Running the application in dev mode

Postgresql
```shell script
docker-compose up -d
```

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```
