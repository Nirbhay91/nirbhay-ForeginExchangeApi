# nirbhay-ForeginExchangeApi

# Forex Exchange Rate Service

This is a Spring Boot application that serves as a microservice for fetching foreign exchange rates from the European Central Bank. It stores the fetched rates in a MySQL database and provides endpoints to access this data. The external API is only called when there is no data available in the database.

## Features

- Fetch exchange rates from USD to specified target currencies (EUR, GBP, JPY, CZK).
- Store fetched exchange rates in a MySQL database.
- Provide REST endpoints to access exchange rates.
- Avoid unnecessary external API calls by checking the database first.

## Technologies Used

- Spring Boot
- Spring Data JPA
- MySQL
- RestTemplate
- Lombok
- JUnit (for testing)

## Prerequisites

- Java 11 or higher
- Maven
- MySQL database

## Setup and Configuration

### Step 1: Clone the Repository

```bash
git clone https://github.com/Nirbhay91/nirbhay-ForeginExchangeApi.git
cd nirbhay-ForeginExchangeApi

### Step 2: Configure Database
Create a MySQL database and configure the connection details in the application.properties file.

# src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/forex_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### Step 3: Build and Run the Application
Build the application using Maven and run it.

mvn clean install
mvn spring-boot:run

Step 4: Testing the Endpoints
Use Postman or any other API client to test the endpoints.

Fetch Latest Exchange Rate

Method: GET
URL: http://localhost:8080/fx?targetCurrency=EUR
Response

{
    "id": 1,
    "date": "2024-07-11",
    "sourceCurrency": "USD",
    "targetCurrency": "EUR",
    "rate": 0.85
}

Fetch Latest 3 Exchange Rates

Method: GET
URL: http://localhost:8080/fx/EUR
Response
[
    {
        "id": 1,
        "date": "2024-07-11",
        "sourceCurrency": "USD",
        "targetCurrency": "EUR",
        "rate": 0.85
    },
    {
        "id": 2,
        "date": "2024-07-10",
        "sourceCurrency": "USD",
        "targetCurrency": "EUR",
        "rate": 0.86
    },
    {
        "id": 3,
        "date": "2024-07-09",
        "sourceCurrency": "USD",
        "targetCurrency": "EUR",
        "rate": 0.87
    }
]


