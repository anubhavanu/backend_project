FROM openjdk:17-jdk-alpine
RUN mkdir -p /app
WORKDIR /app
COPY . /app
#CMD ["./mvnw","spring-boot:run"]
RUN ./mvnw package -Dmaven.test.skip=true
CMD ["java","-jar","target/fileupload-0.0.1-SNAPSHOT.jar”]



