FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/skonimage.war skonimage.war
ENTRYPOINT ["java","-jar","/skonimage.war"]
