#!/bin/sh

echo "build the jar"
./mvnw clean package -DskipTests

echo "move the dependencies"
mkdir -p target/dependency && (cd target/dependency; unzip ../*.jar)

echo "build the docker image"
docker build -t spring-user-crud:1.0.0 .