FROM openjdk:21
COPY System1221-0.0.1-SNAPSHOT-plain.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080