FROM openjdk:8

ADD target/airtel-intern-proj.jar airtel-intern-proj.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/airtel-intern-proj.jar"]
