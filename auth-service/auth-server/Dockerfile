FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENV POSTGRES_HOST $POSTGRES_HOST
ENV RABBITMQ_HOST $RABBITMQ_HOST
CMD java -jar /app.jar