FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/messengeron-0.0.1-SNAPSHOT.jar MessengeronServer.jar
ENTRYPOINT ["java","-jar","MessengeronServer.jar"]