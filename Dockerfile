# base image
FROM eclipse-temurin:21-jdk-alpine

# working dir
WORKDIR /app

# copy jar file
COPY modules/infrastructure/build/libs/user-*.jar app.jar

# entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
