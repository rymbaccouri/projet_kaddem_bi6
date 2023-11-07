FROM openjdk:11
EXPOSE 8089
ADD ./target/projet_kaddem_bi6-1.0.jar projet_kaddem_bi6-1.0.jar
ENTRYPOINT ["java","-jar","/projet_kaddem_bi6-1.0.jar"]