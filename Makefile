build:
	cd apps
	mvn clean install -Dspring.profiles.active=dev -DskipTests=true

data:
	docker-compose -f docker-compose.dev.yml up -d db
	cd apps
	mvn clean install -Dspring.profiles.active=dev -DskipTests=true
	cd loader
	mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true

start:
	docker-compose -f docker-compose.dev.yml up -d db
	cd apps
	mvn clean install -Dspring.profiles.active=dev -DskipTests=true
	cd core
	mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true