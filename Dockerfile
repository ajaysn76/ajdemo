FROM eclipse-temurin:17-jdk-alpine
ADD /build/libs/ajdemo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080