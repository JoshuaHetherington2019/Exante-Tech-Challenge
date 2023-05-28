FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/verifit-0.0.1-SNAPSHOT.jar verifit-server-1.0.0.jar
ENTRYPOINT ["java","-jar","verifit-server-1.0.0.jar"]
EXPOSE 8000