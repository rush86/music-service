FROM openjdk:17-jdk-alpine
MAINTAINER rush86
COPY target/skeleton-0.1-SNAPSHOT.jar skeleton.jar
ENTRYPOINT ["java","-jar","/skeleton.jar"]