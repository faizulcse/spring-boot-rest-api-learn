# Load Test Demo (k6)
 
 This folder contains a small `k6`-based load testing project to exercise the Spring Boot REST API endpoints in this repository.
 
 ## Prerequisites
 
 - **Node.js** (for running the npm scripts)
 - **k6** installed on your machine
   - macOS (Homebrew): `brew install k6`
 - **jq** (optional, only needed for `warning_report` / `error_report` scripts)
   - macOS (Homebrew): `brew install jq`
 
 ## Setup
 
 1. Install Node dependencies:
 
    ```bash
    npm install
    ```
 
 2. Create your environment file:
 
    ```bash
    cp .env.example .env.test
    ```
 
 3. Edit `.env.test` and set the target API:
 
    - `API_URL` - Base URL of the API you want to test (example: `http://localhost:8080`)
    - `TEST_ENV` - Any label you want (example: `local`, `test`, `staging`)
    - `EMAIL`, `PASSWORD` - Present for compatibility; some scripts may not use them

 ## Start the backend API (local)
 
 These k6 tests expect the Spring Boot API to be running.
 
 - **Default base URL** in this repo is:
 
   `http://localhost:9090`
 
 - **Example `.env.test`** value for local run:
 
   `API_URL=http://localhost:9090`
 
 To start dependencies (MySQL) via Docker Compose (optional):
 
 ```bash
 docker compose -f ../compose.yml up -d
 ```
 
 To start the Spring Boot app from the repository root:
 
 ```bash
 mvn spring-boot:run
 ```
 
 ## How it works
 
 - Test scripts live under `tests/`.
 - Common helpers and endpoints are defined in `utils/index.js`.
 - Environment variables are read via k6’s `__ENV`.
 
 ### Supported environment variables
 
 These are read in `utils/index.js`:
 
 - `API_URL` (required)
 - `TEST_ENV` (optional)
 - `TIMEOUT` (optional, default: `60s`)
 - `DEBUG` (optional, default: `false`)
 - `EMAIL` (optional)
 - `PASSWORD` (optional)
 
 ## Run the tests
 
 Each command:
 
 - Clears/creates `output.json`
 - Runs `k6` with JSON logs
 - Writes logs to `./output.json`
 
 ### Application API
 
 ```bash
 npm run test:application
 ```
 
 ### Employee API
 
 ```bash
 npm run test:employee
 ```
 
 ### Load test (multiple endpoints)
 
 ```bash
 npm run test:load_test
 ```
 
 ## Reports from `output.json`
 
 After running any test, you can extract warnings/errors from `output.json`.
 
 - Warnings report:
 
   ```bash
   npm run warning_report
   ```
 
   Generates `warning.csv`.
 
 - Errors report:
 
   ```bash
   npm run error_report
   ```
 
   Generates `errors.csv`.
 
 ## Formatting
 
 ```bash
 npm run format
 ```