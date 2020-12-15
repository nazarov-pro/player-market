## Player Market ##

Player Market software developed as microservices architecture 
contains 2 services (team and player service), besides those 
service discovery(eureka) ang gateway(spring cloud gateway) also implemented.

### Installation ###

Requirements:
- Java 1.8
- Postgres 12 (docker compose file added)
- Docker
- make


#### Step 0. Initializing ####
Initializing docker network. Execute this command ```make init-network```

#### Step 1. Starting External Apps ####

Currently, only RDBMS(postgres) for starting 
RDBMS please execute ```make start-external``` and for stopping
```make stop-external```.

#### Step 2. Starting Internal Apps ####

Internal apps described below:
- Service Discovery (eureka)
- Player Service
- Team Service
- Gateway (Spring cloud gateway)

for starting ```make start-internal```
for stopping ```make stop-internal```

Related Contents

- [Product Requirements](./doc/requirements.md)
- [Components](./doc/component.md)

PS: [Swagger UI](http://localhost:8800/) is available.

PS2: [POSTMAN Collection](configs/PLAYER_MARKET.postman_collection.json) is available.