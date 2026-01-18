# spring-boot-rest-api-learn

## Prerequisites

- Java 17+
- Maven
- Docker Desktop (for Prometheus/Grafana)

## Run Spring Boot

Default port and context path:

- `http://localhost:9090/api/v1`

### Normal mode (requires DB/JPA)

```bash
mvn spring-boot:run
```

### Monitoring mode

Use this mode if you want to expose Actuator/Prometheus metrics:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=monitoring
```

### Metrics-only mode (no DB/JPA)

Use this mode if you only want to expose Actuator/Prometheus metrics (and your DB is not available):

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=metrics-only
```

## Prometheus + Grafana

Start the monitoring stack:

```bash
docker compose -f monitoring/docker-compose.yml up -d
```

Stop the monitoring stack:

```bash
docker compose -f monitoring/docker-compose.yml down
```

URLs:

- Spring Boot Prometheus scrape endpoint: `http://localhost:9090/api/v1/actuator/prometheus`
- Prometheus UI: `http://localhost:9091`
- Grafana UI: `http://localhost:3000` (login: `admin` / `admin`)

See `monitoring/README.md` for details.

## Load tests (k6)

The k6 load test project is inside `load_test_demo/`.

```bash
cd load_test_demo
npm install
npm run test:script
```

See `load_test_demo/README.md` for more.
