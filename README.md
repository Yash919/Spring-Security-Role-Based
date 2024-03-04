# Spring-Security

## Overview
This Spring Security project demonstrates how to implement role-based access control and user authentication using Spring Security in a Spring Boot application. It includes features like user registration, product management, and access control based on user roles.

## Features
- **User Authentication**: Users can register and log in to the system securely.
- **Role-Based Access Control**: Different users have different roles (e.g., ADMIN, USER) and access levels based on their roles.
- **Product Management**: CRUD operations for managing products, with certain operations restricted to specific user roles.
- **RESTful API**: Exposes RESTful endpoints for various operations like product management and user registration.
- **Password Encryption**: Passwords are securely encrypted using BCryptPasswordEncoder before storing in the database.
- **CSRF Protection**: Cross-Site Request Forgery (CSRF) protection is enabled to prevent unauthorized access to sensitive operations.

## Dependencies
- Spring Boot
- Spring Security
- Spring Data JPA
- SQL Database (MySQL Workbench or any other you preferred)

## Database
### UserInfo:
- Entity [**UserInfo.java**] class representing user information.
- Stores user details including username, password and roles.


## Endpoints
### Product Endpoints
- GET /products/welcome:

  - Description: Endpoint to welcome users.
  - Access: Open for everyone.
  - http://localhost:8080/products/welcome
<br>
  <img width="1512" alt="image" src="https://github.com/Yash919/Spring-Security-Role-Based/assets/60219195/f094e56e-fe44-4ead-8694-e8204d631969">


- GET /products/all:

  - Description: Get all products.
  - Access: Requires ROLE_ADMIN authorization.
  - http://localhost:8080/products/all
<br>
  <img width="1512" alt="image" src="https://github.com/Yash919/Spring-Security-Role-Based/assets/60219195/cd2b0988-54c9-4eb2-8eb1-0d888b571e92">


- GET /products/{id}:

  - Description: Get product by ID.
  - Access: Requires ROLE_USER authorization.
  - http://localhost:8080/products/{id}
<br>
  <img width="1512" alt="image" src="https://github.com/Yash919/Spring-Security-Role-Based/assets/60219195/017e1cb6-2188-4dde-b4a8-33e1a1781e56">


- PUT /products/{id}:

  - Description: Update product by ID.
  - Access: Requires ROLE_ADMIN authorization.
  - http://localhost:8080/products/{id}
<br>
  <img width="1512" alt="image" src="https://github.com/Yash919/Spring-Security-Role-Based/assets/60219195/fe7734a5-1651-4695-a5e0-b2edd53b57e5">

- DELETE /products/{id}:

  - Description: Delete product by ID.
  - Access: Requires ROLE_ADMIN authorization.
  - http://localhost:8080/products/{id}
<br>
  <img width="1512" alt="image" src="https://github.com/Yash919/Spring-Security-Role-Based/assets/60219195/f399c5c2-c67e-4671-8968-acf190c48d1a">


- POST /products/newProduct:

  - Description: Add a new product.
  - Access: Requires ROLE_USER authorization.
  - http://localhost:8080/products/newProduct
<br>
  <img width="1512" alt="image" src="https://github.com/Yash919/Spring-Security-Role-Based/assets/60219195/8852df64-faa3-4a58-a03b-e42e66007b5d">


### User Endpoints

- POST /products/new:
  - Description: Add a new user.
  - Access: Open for everyone.
  - http://localhost:8080/products/new
<br>
  <img width="1512" alt="image" src="https://github.com/Yash919/Spring-Security-Role-Based/assets/60219195/c2da88bc-cf67-46b9-9233-61314cddcac2">


## How to Run
- Clone the repository: **git clone <repository-url>**
- Navigate to the project directory: **cd SpringSecurity**
- Build the project: **mvn clean install**
- Run the application: **mvn spring-boot:run**
- Access the application using a **Postman**.

## Credits
This project was created by **-Yash Mehta- 🚀**

