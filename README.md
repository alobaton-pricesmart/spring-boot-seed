# app-name-api

[![Build Status](https://travis-ci.org/alobaton/spring-boot-seed.svg?branch=master)](https://travis-ci.org/alobaton/spring-boot-seed)
[![codecov](https://codecov.io/gh/alobaton/spring-boot-seed/branch/master/graph/badge.svg)](https://codecov.io/gh/alobaton/spring-boot-seed)

Provides fast, reliable and extensible starter for the development of Java projects using Spring Boot.

## How to start?

Before start update the /etc/hosts file:

```bash
# tools
127.0.0.1       db.dev.local
127.0.0.1       db.prod.local
127.0.0.1       api.dev.local
127.0.0.1       api.prod.local
```
Clone the repository:
```bash
$ git clone --depth 1 https://github.com/soportesolicitudesmantenimiento/app-name.git
$ cd app-name/app-name-api
```

Start the database:
```bash
$ docker-compose -f docker-compose.dev.yml up -d db
```

Compile the project:
```bash
$ cd apps
$ mvn clean install -Dspring.profiles.active=dev -DskipTests=true
```

Execute loader:
```bash
$ cd loader
$ mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```

Start authentication and authorization server:
```bash
$ cd ..
$ cd auth
$ mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```
The authentication and authorization server is deployed at http://localhost:8082.

Start the application:
```bash
$ cd ..
$ cd core
$ mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```
The application server is deployed at http://localhost:8083.

To start the projects without Maven use:
```bash
$ java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -DskipTests=true -jar /app.jar
```

## How to test?

To test the project use:
```bash
$ cd apps
$ mvn clean install -Dspring.profiles.active=dev
```

## Dockerización


### Development building and deployment

__NOTA__ Dev don't use Netflix architecture, you must update in change `api.dev.local` to `localhost` in `application-dev.properties` file. Ex. `jdbc:mysql://db:3306/prod-db` where `db` is the container tag in `docker-compose.dev.properties` file.

To start the containers use:
```bash
$ cp .env.dev .env
$ docker-compose -f docker-compose.dev.yml up -d
```

The authentication and authorization server is deployed at http://localhost:8082. The application is deployed at http://localhost:8083.

To start the containers automatically using a Linux server you can use the file `app-name-dev.service`. You must update the project path, move the file to `/etc/systemd/system/` and execute the following commands:
```bash
$ sudo systemctl enable app-name-dev.service
$ sudo systemctl start app-name-dev
```

### Production building and deployment

To start the containers use:
```bash
$ cp .env.prod .env
$ docker-compose -f docker-compose.prod.yml up -d
```

The Eureka and spring boot admin console are available at http://localhost:8081/#/eureka ó http://localhost:8081/admin#/applications.

To start the containers automatically using a Linux server you can use the file `app-name-prod.service`. You must update the project path, move the file to `/etc/systemd/system/` and execute the following commands:
```bash
$ sudo systemctl enable app-name-prod.service
$ sudo systemctl start app-name-prod
```

## Contributors

[<img alt="alobaton" src="https://avatars1.githubusercontent.com/u/9356067?s=460&v=4" width="117">](https://github.com/alobaton)
[<img alt="luisColmenarez" src="https://avatars0.githubusercontent.com/u/16671391?s=400&v=4" width="117">](https://github.com/LuisColmenarez)

## Change Log

## License

[MIT](https://github.com/alobaton/sprin-boot-seed/blob/master/LICENSE)
