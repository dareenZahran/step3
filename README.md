# Perfume Store Backend with Microservices

Welcome to the backend of the Perfume Store, powered by Spring Boot and MySQL in a microservices architecture. This repository serves as the backend implementation for managing perfume data and providing a RESTful API using microservices.

## Setup Instructions

1. **Clone the Repository**: Use `git clone` to clone this repository to your local machine.

2. **Database Configuration**:
    - Ensure you have MySQL installed locally or have access to a MySQL instance.
    - Configure the database connection in each microservice's `application.properties` or `application.yml`.
    - Update the properties like `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` accordingly.

3. **Run the Microservices**:
    - Each microservice can be started individually:
        - Navigate to each microservice directory and run `./mvnw spring-boot:run`.
        - Or use your IDE (like IntelliJ IDEA, Eclipse) to run each microservice.
    - The services will start at different ports by default.

4. **API Endpoints**:
    - Explore the available endpoints using tools like Postman or cURL.
    - Each microservice will have its own set of endpoints.
    - The base URL for each microservice's API requests will be different, based on the port it's running on.

## Project Structure

- **Microservices**:
    - **Perfume Service**: Manages perfume data (entities, repositories, services, controllers).
    - **User Service**: Handles user-related functionalities (authentication, profiles, etc.).


## Dependencies

- **Spring Boot**: Provides the framework for creating and running each microservice.
- **Spring Cloud**: Offers tools for building microservices.
- **Spring Data JPA**: Simplifies data access using ORM (Object-Relational Mapping).
- **MySQL Driver**: Connector to interact with MySQL database.


## Contributors

Teame members : Dareen Zahran , Salsabeel Alsahoury , Aya Abuali

Thank you for exploring the backend of the Perfume Store with Microservices! 🌸
