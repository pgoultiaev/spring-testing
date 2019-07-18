#! /bin/bash
# create a network to be able to work in a remote container in vscode
# docker network create --driver bridge spring-testing

docker stop demo-postgres
docker rm demo-postgres
docker run --name demo-postgres --network spring-testing -p 15432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=testuser -d postgres
