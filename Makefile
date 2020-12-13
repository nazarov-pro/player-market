start-db:
	docker-compose -f configs/docker/postgres/postgres.compose.yml up -d
stop-db:
	docker-compose -f configs/docker/postgres/postgres.compose.yml down
start-service-discovery:
	./gradlew :service-discovery:build
	java -jar service-discovery/build/libs/service-discovery-1.0.0.jar
start-player-service:
	./gradlew :player-service:build
	java -jar player-service/build/libs/player-service-1.0.0.jar
start-team-service:
	./gradlew :team-service:build
	java -jar team-service/build/libs/team-service-1.0.0.jar
start-gateway:
	./gradlew :gateway:build
	java -jar gateway/build/libs/gateway-1.0.0.jar