#FROM gradle AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --no-daemon
#
#FROM openjdk:8-jdk-alpine
#
#RUN mkdir /app
#
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
#
#ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]

FROM openjdk:8-jdk-alpine
COPY /build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]

