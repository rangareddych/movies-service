FROM openjdk:17
ADD target/movie-service.jar movie-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "movie-service.jar"]