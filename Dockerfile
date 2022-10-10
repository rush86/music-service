FROM openjdk:17-jdk-alpine
MAINTAINER rush86
COPY target/music-service-0.1-SNAPSHOT.jar music-service.jar
ENTRYPOINT ["java","-jar","/music-service.jar"]