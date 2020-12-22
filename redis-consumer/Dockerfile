# Use JRE11 slim
FROM openjdk:8-jdk-alpine

# Add the app jar
ADD target/*.jar redis-stream.jar

ENTRYPOINT java -jar redis-stream.jar