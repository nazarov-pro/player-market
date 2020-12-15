init-network:
	docker network create -d bridge db-player-market-network
	docker network create -d bridge backend-player-market-network

start-external: start-db
stop-external: stop-db

start-db:
	docker-compose -f configs/docker/postgres/postgres.compose.yml up -d
stop-db:
	docker-compose -f configs/docker/postgres/postgres.compose.yml down

start-internal: start-service-discovery start-player-service start-team-service start-gateway
stop-internal: stop-gateway stop-player-service stop-team-service stop-service-discovery

start-service-discovery:
	./gradlew :service-discovery:build
	docker-compose -f service-discovery/docker-compose.yaml up -d
stop-service-discovery:
	docker-compose -f service-discovery/docker-compose.yaml down

start-player-service:
	./gradlew :player-service:build
	docker-compose -f player-service/docker-compose.yaml up -d
stop-player-service:
	docker-compose -f player-service/docker-compose.yaml down

start-team-service:
	./gradlew :team-service:build
	docker-compose -f team-service/docker-compose.yaml up -d
stop-team-service:
	docker-compose -f team-service/docker-compose.yaml down

start-gateway:
	./gradlew :gateway:build
	docker-compose -f gateway/docker-compose.yaml up -d
stop-gateway:
	docker-compose -f gateway/docker-compose.yaml down
