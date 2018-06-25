FROM java:8

COPY target/ictlab-0.2.0.jar app.jar

EXPOSE 8000:8000

CMD java -jar app.jar
