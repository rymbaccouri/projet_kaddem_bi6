FROM openjdk:11
EXPOSE 8090
ADD target/kaddem-0.0.1.jar kaddem-0.0.1.jar
ENTRYPOINT ["java","-jar","/kaddem-0.0.1.jar"]