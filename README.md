# Perfume Store Backend

Welcome to the backend of the Perfume Store, powered by Spring Boot and MySQL. This repository serves as the backend implementation for managing perfume data and providing a RESTful API.

## Setup Instructions

1. **Clone the Repository**: Use `git clone` to clone this repository to your local machine.

2. **Database Configuration**:
    - Ensure you have MySQL installed locally or have access to a MySQL instance.
    - Configure the database connection in `application.properties` or `application.yml`.
    - Update the properties like `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` accordingly.

3. **Run the Application**:
    - Use your IDE (like IntelliJ IDEA, Eclipse) or run `./mvnw spring-boot:run` command to start the Spring Boot application.
    - The application will start at `http://localhost:8080` by default.

4. **API Endpoints**:
    - Explore the available endpoints using tools like Postman or cURL.
    - The base URL for API requests is `http://localhost:8080/api`.

## Project Structure

- **Entities**: Contains Java classes annotated with `@Entity` representing database tables (e.g., `Perfume`).
- **Repositories**: Includes interfaces extending `JpaRepository` to perform CRUD operations on entities.
- **Services**: Holds classes handling business logic, utilizing repositories.
- **Controllers**: Houses RESTful API endpoints using `@RestController` to handle HTTP requests.

## Dependencies

- **Spring Boot**: Provides the framework for creating and running the application.
- **Spring Data JPA**: Simplifies data access using ORM (Object-Relational Mapping).
- **MySQL Driver**: Connector to interact with MySQL database.

## Further Development

Feel free to expand this backend by adding:
- Security configurations (e.g., JWT, OAuth).
- Exception handling and validations.
- Advanced querying using Specifications or Criteria API.
- Unit and integration tests for robustness.

## Contributors

- Add your name here if contributing to this project.

Thank you for exploring the backend of the Perfume Store! 🌸
