# User Microservice CRUD sample

## Dependencies

* Java 17

## About

The service contains total of five REST APIs for performing sample CRUD in Spring Boot. It also uses H2 database.
Additionally, there is also ongoing unit testing sample. My plan is to cover the following layers: controller, service(
business layer), and repository testing. Remember this project is for demo purpose only.

## Project Structure

This project uses layered and clean architecture(by Uncle Bob). `web` contains the controller which invokes the service.
`service` contains the business logic, it depends only on abstraction or interface. No framework classes should be used
in the service. Lastly, `repository` contains the persistence layer which communicates with the database.

## Postman collection

In the project root, there is a folder named `postman_collections` which contains the collection for this project. Just

## How to use it?

Just open it on Intellij and run as Spring boot application.
