CREATE DATABASE team;
CREATE USER team WITH ENCRYPTED PASSWORD 'secured';
GRANT ALL PRIVILEGES ON DATABASE team TO team;

CREATE DATABASE player;
CREATE USER player WITH ENCRYPTED PASSWORD 'secured';
GRANT ALL PRIVILEGES ON DATABASE player TO player;