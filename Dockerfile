FROM openjdk:11
EXPOSE 8089
ADD target/	tn/esprit/spring/kaddem/0.0.1/kaddem-0.0.1.jar 	tn/esprit/spring/kaddem/0.0.1/kaddem-0.0.1.jar
ENTRYPOINT ["java","-jar","/tn/esprit/spring/kaddem/0.0.1/kaddem-0.0.1.jar"]


