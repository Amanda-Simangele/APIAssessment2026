# Ndosi Automation API Tests

## Overview
Lightweight Java + Maven test suite using RestAssured to exercise Ndosi API endpoints.

Key locations
- `src/test/java/RequestBuilder/NdosiRequest.java` — request helpers and shared test state (`authToken`, `userId`).
- `src/test/java/Common/URIs.java` — set the API `baseURL` here.
- `src/test/java/Common/DataGenerator.java` — (or add) static generated variables (email, name, surname) for payloads.
- `src/test/java/PayloadBuilder/NdosiPayload.java` — payload builders used by requests.

## Prerequisites
- Java (11+ or project Java version)
- Maven
- (Optional) Allure CLI to view reports locally: https://docs.qameta.io/allure/

## Run tests (Windows cmd, run from project root)
Run all tests (recommended):

```cmd
mvn clean test
```

Run a single test class (replace ClassName):

```cmd
mvn -Dtest=ClassName test
```

Run a single test method (replace ClassName and methodName):

```cmd
mvn -Dtest=ClassName#methodName test
```

## Allure report (two options)
If you have the Allure CLI installed and want to serve the existing results directory directly (Windows), run:

```cmd
allure serve C:\Automation2026\APIAssessment2026\allure-results
```

Or generate and open a static report from the results created by the Maven run:

```cmd
mvn clean test
allure generate target/allure-results -o target/allure-report
allure open target/allure-report
```

If you don't have Allure CLI, you can install it (see https://docs.qameta.io/allure/).

Note: this project already contains an `allure-results/` folder — running `mvn clean test` will produce new result files under `target/allure-results` or the configured output location depending on the Maven/allure plugin in the project.

## Where to change test data
- Edit `src/test/java/Common/URIs.java` to change `baseURL`.
- Add or update generated variables in `src/test/java/Common/DataGenerator.java` (public static fields) such as `email`, `name`, `surname` so other classes can reference them directly (e.g., `DataGenerator.email`).

Example (what to add to `DataGenerator.java`):
- `public static String email = "testuser@example.com";`
- `public static String name = "John";`
- `public static String surname = "Doe";`

## Logging note
- `.log().all()` prints full request/response (headers, status, body) to console for debugging. It may print sensitive headers (Authorization). Use `.log().ifValidationFails()` in CI to reduce noise and avoid exposing secrets.

## Troubleshooting
- If a test fails with `User not found` for an approve/update endpoint, ensure the test flow creates a user and extracts `userId` before calling admin endpoints. Check the printed requests/responses (or use `log().ifValidationFails()`).

---

If you'd like, I can: add a `Common/DataGenerator.java` with Faker-based public static variables (`email`, `name`, `surname`), or adjust tests to log only on failure. Which would you like me to do next?
