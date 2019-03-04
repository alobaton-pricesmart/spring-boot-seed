# spring-boot-seed

[![Build Status](https://travis-ci.org/alobaton/spring-boot-seed.svg?branch=master)](https://travis-ci.org/alobaton/spring-boot-seed)
[![codecov](https://codecov.io/gh/alobaton/spring-boot-seed/branch/master/graph/badge.svg)](https://codecov.io/gh/alobaton/spring-boot-seed)

Provides fast, reliable and extensible starter for the development of Java projects using Spring Boot.

`spring-boot-seed` provides the following features:

- Modularized Maven project.
- i18n support.
- Application masters module.
- Application settings module.
- Application loader module.
- Production and development builds.
- Provides full Docker support for both development and production environment.
- Swagger UI documentation at http://localhost:8080/swagger-ui.html

## How to start?

In order to start the seed use:

```bash
$ git clone --depth 1 https://github.com/alobaton/spring-boot-seed.git
$ cd spring-boot-seed

$ mvn clean install -Dspring.profiles.active=dev

$ cd admin

$ mvn spring-boot:run -Dspring.profiles.active=dev

# without maven
$ java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -jar /app.jar
```

## How to test?

In order to test the seed user:

```bash
# startup the dev database.
$ docker-compose -f docker-compose.dev.yml up -d db

$ mvn clean install -Dspring.profiles.active=dev
# skip the tests on development.
mvn clean install -Dspring.profiles.active=dev -DskipTests=true
```

## Dockerization

The application provides full Docker support. You can use it for both development and production builds and deployments.

### Development build and deployment

To start the container, use:

```bash
$ cp .env.dev .env
$ docker-compose -f docker-compose.dev.yml up -d
```

Now open your browser at http://localhost:8080

### Production build and deployment

It is self-contained, and can therefore be pushed to a Docker registry to be deployed somewhere else easily.

To start the container, use:

```bash
$ cp .env.prod .env
$ docker-compose -f docker-compose.prod.yml up -d
```

Now open your browser at http://localhost:8080

## Contributors

[<img alt="alobaton" src="https://avatars1.githubusercontent.com/u/9356067?s=460&v=4" width="117">](https://github.com/alobaton)

## Change Log

## License

[MIT](https://github.com/alobaton/sprin-boot-seed/blob/master/LICENSE)
