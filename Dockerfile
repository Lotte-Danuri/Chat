FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/Chat-0.0.1-SNAPSHOT.jar ChatServer.jar
ENTRYPOINT ["java","-jar","ChatServer.jar"]