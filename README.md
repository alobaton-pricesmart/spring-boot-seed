# solicitudes-mantenimiento-api

## Como iniciar?

Antes de iniciar actualice el archivo /etc/hosts:

```bash
# tools
127.0.0.1       db.dev.local
127.0.0.1       db.prod.local
127.0.0.1       api.dev.local
127.0.0.1       api.prod.local
```
Clone el repositorio:
```bash
$ git clone --depth 1 https://github.com/soportesolicitudesmantenimiento/solicitudes-mantenimiento.git
$ cd solicitudes-mantenimiento/solicitudes-mantenimiento-api
```

Inicie la Base de Datos de pruebas:
```bash
$ docker-compose -f docker-compose.dev.yml up -d db
```

Compile el proyecto:
```bash
$ cd apps
$ mvn clean install -Dspring.profiles.active=dev -DskipTests=true
```

Ejecute el loader:
```bash
$ cd loader
$ mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```

Ejecute el servicio de Autenticación y Autorización:
```bash
$ cd ..
$ cd auth
$ mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```
El servicio de Autenticación y Autorización se encuentra disponible en http://localhost:8082.

Ejecute la aplicación:
```bash
$ cd ..
$ cd core
$ mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```
La aplicación se encuentra disponible en http://localhost:8083.

Para ejecutar los anteriores proyectos sin Maven use:
```bash
$ java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -DskipTests=true -jar /app.jar
```

## Como probar?

Para probar el proyecto use:
```bash
$ cd apps
$ mvn clean install -Dspring.profiles.active=dev
```

## Dockerización

La aplicación provee soporte completo para Docker. Puedes usarlo para construcción y despliegue de desarrollo y producción.

### Construcción y despliegue de desarrollo

__NOTA__ El entorno dev no utiliza la arquitectura Netflix, razón por la cual deberá modificar los `application-dev.properties` donde se haga referencia a `api.dev.local` o `localhost` y reemplazarlo por la respectiva etiqueta del contenedor. Ej. `jdbc:mysql://db:3306/prod-db` donde `db` es la etiqueta del contenedor de base de datos en el archivo `docker-compose.dev.yml`.

Para iniciar los contenedores use:
```bash
$ cp .env.dev .env
$ docker-compose -f docker-compose.dev.yml up -d
```

El servicio de Autenticación y Autorización se encuentra disponible en http://localhost:8082. La aplicación se encuentra disponible en http://localhost:8083.

Para iniciar los contenedores automaticamente al iniciar un servidor Linux, puede utilizar el archivo `solicitudes-mantenimiento-dev.service`. Debe actualizar la ruta el proyecto y moverlo a la ruta `/etc/systemd/system/` y ejecutando los siguientes comandos:
```bash
$ sudo systemctl enable solicitudes-mantenimiento-dev.service
$ sudo systemctl start solicitudes-mantenimiento-dev
```

### Construcción y despliegue de producción

Para iniciar los contenedores use:
```bash
$ cp .env.prod .env
$ docker-compose -f docker-compose.prod.yml up -d
```

Ahora abra su navegador en http://localhost:8081/#/eureka ó http://localhost:8081/admin#/applications.

Para iniciar los contenedores automaticamente al iniciar un servidor Linux, puede utilizar el archivo `solicitudes-mantenimiento-prod.service`. Debe actualizar la ruta el proyecto y moverlo a la ruta `/etc/systemd/system/` y ejecutando los siguientes comandos:
```bash
$ sudo systemctl enable solicitudes-mantenimiento-prod.service
$ sudo systemctl start solicitudes-mantenimiento-prod
```