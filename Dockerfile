FROM maven:3.6.3-jdk-11
COPY src /tmp/src
COPY pom.xml /tmp
WORKDIR /tmp
ENTRYPOINT ["mvn","verify"]