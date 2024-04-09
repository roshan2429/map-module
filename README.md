# Spring Boot Project Documentation

## Project Overview

This project is a Spring Boot web application that interfaces with Google Cloud Spanner to retrieve and display service provider information on a map based on a user-specified radius. It follows the MVC (Model-View-Controller) pattern and is structured as follows:

### Components

- **Model Layer (`User.java`)**
- **Repository Layer (`UserRepository.java`)**
- **Service Layer (`UserService.java`)**
- **Controller Layer (`UserController.java`)**
- **Static Resources (`map.html` and `script.js`)**

### Model Layer (`User.java`)

Represents entities in the application. For the `User` entity:

- Annotated with `@Table(name = "Users")` to map the class to the `Users` table in Cloud Spanner.
- Fields like `id`, `name`, `latitude`, `longitude`, and `isActive` reflect the table's columns.
- Includes getter methods such as `isActive()`, `getLatitude()`, and `getLongitude()`.

### Repository Layer (`UserRepository.java`)

- Extends `SpannerRepository` from Spring Data for CRUD operations on the `User` entity.
- The repository interface can contain custom query methods if needed.

### Service Layer (`UserService.java`)

- Contains business logic for filtering users within a specified radius.
- Fetches users via `UserRepository` and filters them in `findUsersWithinRadius()`.
- Uses `calculateDistance()` method, which employs the Haversine formula to calculate distances between geographic coordinates.

### Controller Layer (`UserController.java`)

- Handles web requests and sends responses.
- Annotated with `@RestController` and `@RequestMapping` to define the RESTful nature and the URL path.
- `getUsersWithinRadius()` method handles GET requests and utilizes services to respond with the required user data.

### Static Resources (`map.html` and `script.js`)

- `map.html` provides the HTML structure for the map view, including input fields for user data.
- `script.js` handles the form submission, initializes the Leaflet map, and adds markers for users within the specified radius after fetching data from the backend.

## Application Flow

1. User interacts with `map.html` by entering latitude, longitude, and radius.
2. On form submission, `script.js` captures the input and makes an AJAX call to the backend.
3. The `UserController` in the backend processes the request.
4. Parameters are passed to `UserService`, which retrieves and filters users.
5. `UserService` returns the filtered list to the `UserController`.
6. `UserController` sends this list back to the frontend.
7. `script.js` processes the response and places markers for each user on the map.

## Conclusion

Each component of the project has a specific responsibility, allowing for a clean separation of concerns. The project demonstrates a full-stack Java application using Spring Boot, integrating with a cloud database, and rendering interactive client-side maps.
