FROM openjdk:11
COPY target/schoolmanagementsystem-0.0.1-SNAPSHOT.jar schoolmanagementsystem-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","schoolmanagementsystem-0.0.1-SNAPSHOT.jar"]