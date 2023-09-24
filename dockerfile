FROM openjdk:17-jdk-alpine
RUN mkdir -p /app
WORKDIR /app
COPY . /app
RUN ls
RUN ls target/
CMD ["./mvnw","spring-boot:run"]