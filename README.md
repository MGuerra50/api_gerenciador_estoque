# API Finance Organizer

_A Spring Boot REST API for managing personal finances, leveraging PostgreSQL for data persistence and Flyway for database migrations._

---

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Docker** (to run the PostgreSQL database in a container)  
- **Java 17** or higher  
- **Maven Wrapper** (included in the project)  

---

## Setup & Running

Follow these steps to get the application up and running:

### 1. Start the PostgreSQL container

In the root directory of the project (where `docker-compose.yml` resides), run:

```bash
docker-compose up -d


This will:

- Pull the official PostgreSQL image  
- Create a container named `postgres-spring`  
- Expose the database on the configured port  

---

## Run the API

With the database container running, start the Spring Boot application:

### On Unix/macOS (Git Bash, Linux, macOS)

```bash
./mvnw spring-boot:run

# On Windows (PowerShell or CMD)
.mvnw.cmd spring-boot:run

The API will be accessible at http://localhost:8080 by default.
