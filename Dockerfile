FROM openjdk:11-jre-slim
WORKDIR /app
ADD target/*.jar kaddem-0.0.3-SNAPSHOT.jar
EXPOSE 8089
CMD ["java", "-jar", "kaddem-0.0.3-SNAPSHOT.jar"]