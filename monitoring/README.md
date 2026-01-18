# Prometheus + Grafana (Spring Boot)

This project exposes Spring Boot metrics via **Actuator + Micrometer** and ships a local **Prometheus + Grafana** stack under `monitoring/`.

## Prerequisites

- Java 17+
- Maven
- Docker Desktop (macOS/Windows)

## 1) Start Spring Boot

Run the Spring Boot application (it must be reachable on your host machine):

If you do not want to run MySQL locally (or your DB is not reachable), start the app using the `monitoring` profile which disables JDBC/JPA auto-configuration:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=monitoring
```

- App base URL: `http://localhost:9090/api/v1`
- Prometheus scrape endpoint: `http://localhost:9090/api/v1/actuator/prometheus`

Example check:

```bash
curl http://localhost:9090/api/v1/actuator/prometheus | head
```

If this does not return Prometheus-formatted text, verify:

- `management.endpoints.web.exposure.include` contains `prometheus`
- `io.micrometer:micrometer-registry-prometheus` dependency exists
- The app is running on port `9090`

## 2) Start Prometheus + Grafana (Docker)

From the repository root:

```bash
docker compose -f monitoring/docker-compose.yml up -d
```

Ports:

- Prometheus UI: `http://localhost:9091`
- Grafana UI: `http://localhost:3000`

Grafana login:

- Username: `admin`
- Password: `admin`

## 3) Verify Prometheus is scraping Spring Boot

### Option A: Prometheus UI

Open:

- `http://localhost:9091/targets`

You should see a target for job name `spring-boot` with status **UP**.

### Option B: Query a metric

Open:

- `http://localhost:9091/graph`

Try these queries:

- `tomcat_threads_busy_threads`
- `tomcat_threads_current_threads`
- `http_server_requests_seconds_count`
- `jvm_memory_used_bytes`

## 4) View results in Grafana

Open Grafana:

- `http://localhost:3000`

Prometheus datasource is already provisioned from:

- `monitoring/grafana/provisioning/datasources/datasource.yml`

### Pre-provisioned dashboard

This repository also provisions a dashboard automatically:

- Folder: `Spring Boot`
- Dashboard: `Spring Boot API Overview`

Files:

- Dashboard provider: `monitoring/grafana/provisioning/dashboards/provider.yml`
- Dashboard JSON: `monitoring/grafana/provisioning/dashboards/spring-boot-api-overview.json`

If you change provisioning files, restart the containers so Grafana reloads them:

```bash
docker compose -f monitoring/docker-compose.yml down
docker compose -f monitoring/docker-compose.yml up -d
```

### Import a dashboard

In Grafana:

- Go to **Dashboards**
- Click **New** -> **Import**

Common dashboards to search for (community dashboards):

- JVM / Micrometer
- Spring Boot / Micrometer

Pick datasource: **Prometheus**.

## 5) Stop the monitoring stack

```bash
docker compose -f monitoring/docker-compose.yml down
```

## Notes

- Prometheus scrapes your Spring Boot app using `host.docker.internal:9090`. This is standard on macOS Docker Desktop.
- If you run Docker on Linux, `host.docker.internal` may not resolve by default; you would need a different target configuration.
