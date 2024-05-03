FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/bookstore-0.0.1-SNAPSHOT.jar bookstore.jar
ENTRYPOINT ["java","-jar","bookstore.jar"]