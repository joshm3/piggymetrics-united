FROM openjdk:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/united-monolith.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/united-monolith.jar"]

EXPOSE 6001