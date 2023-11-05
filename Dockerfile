FROM openjdk:11-jre-slim
WORKDIR /app
ADD kaddem-2.0.0.jar kaddem-2.0.0.jar
EXPOSE 8089
CMD ["java", "-jar", "kaddem-2.0.0.jar"]