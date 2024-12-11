FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/projetimage.war projetimage.war
ENTRYPOINT ["java","-jar","/projetimage.war"]