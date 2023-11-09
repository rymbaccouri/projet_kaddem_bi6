FROM openjdk:11
EXPOSE 8089
<<<<<<< HEAD
ADD target/kaddem-0.0.1-SNAPSHOT.jar kaddem-0.0.1.jar
ENTRYPOINT ["java","-jar","/kaddem-0.0.1.jar"]
=======
ADD target/kaddem-0.0.1.jar kaddem-0.0.1.jar
ENTRYPOINT ["java","-jar","/kaddem-0.0.1.jar"]
>>>>>>> origin/Departement
