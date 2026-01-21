Run command: 1

- ```mvn spring-boot:run```
- ```LOGGER_ENABLED=true mvn spring-boot:run```
- ```LOGGER_ENABLED=false mvn spring-boot:run```
- ```http://localhost:9090/api/v1/app_info```

Run command: 2
- ```docker compose -f compose.yml up -d```
- ```docker compose -f compose.yml down```
- ```mvn spring-boot:run```
- ```http://localhost:9090/api/v1/app_info```

