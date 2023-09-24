FROM openjdk:17-jdk-alpine
RUN mkdir -p /app
WORKDIR /app
COPY . /app
CMD ["./mvnw","spring-boot:run"]