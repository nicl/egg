FROM java:8

COPY target/uberjar/*-standalone.jar /usr/src/app.jar

ENTRYPOINT ["java", "-jar", "/usr/src/app.jar"]
