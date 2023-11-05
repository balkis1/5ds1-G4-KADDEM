FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/kaddem-1.0.0.jar /app/kaddemapp.jar
EXPOSE 8089
CMD ["java", "-jar", "kaddemapp.jar"]