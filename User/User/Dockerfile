FROM openjdk:8
EXPOSE 9002
ADD target/User.jar User.jar
ARG JAR_FILE=target/*.jar
ENTRYPOINT ["java","-jar","/User.jar"]

