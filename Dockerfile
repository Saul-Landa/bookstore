FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/bookstore-0.0.1-SNAPSHOT.jar bookstore.jar
ADD src/main/resources/static src/main/resources/static
ENTRYPOINT ["java","-jar","bookstore.jar"]