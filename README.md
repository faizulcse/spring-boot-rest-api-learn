# spring-boot-rest-api-learn

Spring Boot REST API project (Java 17, Maven, Spring Boot 3.x) with MySQL + Spring Data JPA.

## Prerequisites

- **Java**: 17
- **Maven**: 3.8+
- **MySQL**: local installation OR run via Docker Compose

## Configuration

Default settings are in `src/main/resources/application.properties`:

- **Server port**: `9090`
- **Context path**: `/api/v1`
- **DB**: `jdbc:mysql://localhost:3306/restapi`
- **DB user/pass**: `root` / `root`
- **DDL**: `spring.jpa.hibernate.ddl-auto=update`
- **Log file**: `logs/app.log`

If your local DB credentials differ, update:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

## Database setup (local MySQL)

Create the database:

```sql
CREATE DATABASE restapi;
```

## Run the application

### Option A: Run locally (Maven)

From the project root:

```bash
mvn spring-boot:run
```

If you want to control request logging via the `LOGGER_ENABLED` environment variable:

```bash
LOGGER_ENABLED=true mvn spring-boot:run
```

```bash
LOGGER_ENABLED=false mvn spring-boot:run
```

### Option B: Run MySQL via Docker Compose

Start dependencies:

```bash
docker compose -f compose.yml up -d
```

Run the app:

```bash
mvn spring-boot:run
```

Stop dependencies:

```bash
docker compose -f compose.yml down
```

## Verify the app is running

Open:

- `http://localhost:9090/api/v1/app_info`

## Notes

- If port `9090` is already in use, change `server.port` in `application.properties`.
- The base URL for APIs is: `http://localhost:9090/api/v1`
