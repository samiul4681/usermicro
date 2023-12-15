FROM openjdk:21-slim
COPY target/usermicro-0.0.1-SNAPSHOT.jar usermicro-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usermicro-0.0.1-SNAPSHOT.jar"]