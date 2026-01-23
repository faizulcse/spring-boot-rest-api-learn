# spring-boot-rest-api-learn

Spring Boot REST API project (Java 17, Maven, Spring Boot 3.x) with MySQL + Spring Data JPA.

Base URL (default): `http://localhost:9090/api/v1`

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
- **App name/version**: `app.name`, `app.version`
- **Rate limit** (Bucket4j): `app.ratelimit.*`

If your local DB credentials differ, update:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

### Request logging

The request filter prints simple request logs to stdout when the `LOGGER_ENABLED` environment variable is set to `true`.

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

Build with no cache:
```bash
docker compose -f compose.yml build --no-cache app
```

Run with detach mode:
```bash
docker compose -f compose.yml up -d
```

Run without detach:
```bash
docker compose -f compose.yml up
```

Run the app:

```bash
mvn spring-boot:run
```

Stop dependencies:

```bash
docker compose -f compose.yml down
```

### Option C: Run the full stack via Docker Compose (MySQL + app)

The `compose.yml` includes both `mysql` and `app` services.

```bash
docker compose -f compose.yml up --build
```

## Verify the app is running

Open:

- `http://localhost:9090/api/v1/app_info`

## API endpoints

All routes below are relative to the base URL: `http://localhost:9090/api/v1`

### Monitoring

- `GET /app_info`
  - Returns JSON with `name`, `version`, `port`, `context_path`.
- `GET /internal/inflight`
  - Returns current in-flight request count from the request filter.

### Employees

- `GET /employees?page_number=<int>&page_size=<int>`
- `GET /employees/{id}`
- `POST /employees`
- `PUT /employees/{id}`
- `DELETE /employees?id=<long>`
- `GET /employees/filter_by_name?name=<string>`
- `GET /employees/filter_by_name_and_location?name=<string>&location=<string>`
- `GET /employees/filter_by_keyword?name=<string>`
- `GET /employees/{name}/{location}`
- `DELETE /employees/delete/{name}`

### Load test

- `GET /api_with_header_data`
  - Requires header `scenario_id: <string>`
- `GET /api_with_path_data/{scenario_id}`
- `GET /normal_api`
- `GET /normal_sync_api`
- `GET /fixed_delay_api?milliseconds=<long>`
- `GET /random_delay_api?milliseconds=<long>`
- `GET /fixed_delay_sync_api?milliseconds=<long>`
- `GET /random_delay_sync_api?milliseconds=<long>`
- `POST /print_body_api`
  - Body: JSON object

Examples:

```bash
curl -i http://localhost:9090/api/v1/normal_api
curl -i -H 'scenario_id: 1' http://localhost:9090/api/v1/api_with_header_data
curl -i http://localhost:9090/api/v1/api_with_path_data/1
curl -i 'http://localhost:9090/api/v1/fixed_delay_api?milliseconds=1000'
curl -i -X POST http://localhost:9090/api/v1/print_body_api -H 'Content-Type: application/json' -d '{"ping":"pong"}'
```

## Rate limiting

Rate limiting is implemented via Bucket4j in `RequestFilter` and is controlled by:

- `app.ratelimit.enabled` (default `true`, currently set to `false` in `application.properties`)
- `app.ratelimit.capacity`
- `app.ratelimit.refill-tokens`
- `app.ratelimit.refill-seconds`

When enabled, the filter returns:

- `429 Too Many Requests`
- `Retry-After: <seconds>`
- `X-Rate-Limit-Remaining: <tokens>` (when request is allowed)

## Troubleshooting

- **Port already in use**
  - Change `server.port` in `application.properties`.
- **Cannot connect to MySQL**
  - Ensure MySQL is running and the database `restapi` exists.
  - If running via Docker Compose, note that the app uses `jdbc:mysql://mysql:3306/restapi...` inside the compose network.

## Notes

- If port `9090` is already in use, change `server.port` in `application.properties`.
- The base URL for APIs is: `http://localhost:9090/api/v1`
