[![CI](https://github.com/tkgregory/optional-examples/actions/workflows/gradle.yml/badge.svg)](https://github.com/tkgregory/optional-examples/actions/workflows/gradle.yml)

This repository contains 2 projects to help understand Java's `Optional` class.

## 1. guitarist-api

A full Spring Boot API application to demonstrate a real-world usage of `Optional`.

### Building & testing

```bash
cd guitarist-api
./gradlew build
```

### Running
```bash
cd guitarist-api
./gradlew bootRun
```

### Interacting with API

```bash
curl http://localhost:8080/guitarist/hendrix
```

## 2. optional-test-cases

A suite of test cases to demonstrate all methods available on `Optional`.

### Building & testing

```bash
cd optional-test-cases
./gradlew build
```