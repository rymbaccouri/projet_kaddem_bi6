FROM openjdk:11
EXPOSE 8090
ADD target/alpine:1.0.0.jar alpine:1.0.0.jar
ENTRYPOINT ["java","-jar","/alpine:1.0.0.jar"]