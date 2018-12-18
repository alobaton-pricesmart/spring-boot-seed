# spring-boot-seed

Provides fast, reliable and extensible starter for the development of Java projects using Spring Boot.

`spring-boot-seed` provides the following features:

- Modularized Maven project.
- i18n support.
- Application masters module.
- Application settings module.
- Application loader module.
- Production and development builds.
- Provides full Docker support for both development and production environment.

## Dockerization

The application provides full Docker support. You can use it for both development and production builds and deployments.

### Development build and deployment

To start the container, use:

```bash
$ docker-compose -f docker-compose.dev.yml up -d
```

Now open your browser at http://localhost:8080

### Production build and deployment

It is self-contained, and can therefore be pushed to a Docker registry to be deployed somewhere else easily.

To start the container, use:

```bash
$ docker-compose -f docker-compose.prod.yml up -d
```

Now open your browser at http://localhost:8080

## Contributors

[<img alt="alobaton" src="https://avatars1.githubusercontent.com/u/9356067?s=460&v=4" width="117">](https://github.com/alobaton)

## Change Log

## License

MIT
