FROM openjdk:11.0.16
EXPOSE 7070:7070



RUN mkdir /app

#WORKDIR /app

COPY ./build/libs/*-all.jar /app/freshnearme.jar
ENTRYPOINT ["java","-jar","/app/freshnearme.jar"]