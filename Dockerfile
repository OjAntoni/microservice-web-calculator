
#FROM maven:3.5-jdk-11 AS build
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY  target/monolith-web-calculator-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
#COPY target/monolith-web-calculator-0.0.1-SNAPSHOT.jar /opt/
ENTRYPOINT ["java", "-jar", "monolith-web-calculator-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "./opt/monolith-web-calculator-0.0.1-SNAPSHOT.jar"]

