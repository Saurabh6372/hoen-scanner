# hoen-scanner

A **Dropwizard (Java) microservice** that searches car rentals and hotels for a given city, returning JSON results over a REST endpoint.

Built as part of **Skyscanner's Software Engineering Job Simulation** (Forage, 2026).

## What it does

- Exposes a `POST /search` endpoint accepting a search request (e.g. a city name).
- Loads car-rental and hotel data from bundled JSON resources.
- Returns matching results as a combined, typed JSON response.

## Stack

Java · Dropwizard · Jackson · Maven

## Run

```bash
mvn clean install
java -jar target/*.jar server config.yml
```

Then query it:

```bash
curl -X POST http://localhost:8080/search \
  -H "Content-Type: application/json" \
  -d '{"city": "rome"}'
```
