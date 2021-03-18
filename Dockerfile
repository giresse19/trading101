FROM openjdk:8-jdk-alpine

WORKDIR /app
ARG ARTIFACT_PATH
COPY target/${ARTIFACT_PATH} app.jar

EXPOSE 8080 8081
ENTRYPOINT [ "java","-jar", "/app/app.jar", "-X" ]
