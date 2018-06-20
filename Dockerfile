FROM java:8

COPY target/ictlab-0.2.0.jar app.jar

CMD java -jar app.jar

EXPOSE 8000
