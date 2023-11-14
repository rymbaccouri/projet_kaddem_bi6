FROM openjdk:11-jre-slim
EXPOSE 8089
WORKDIR /app

RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-2.1.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/kaddem/2.1/kaddem-2.1.jar"

ENTRYPOINT ["java", "-jar", "kaddem-2.1.jar"]