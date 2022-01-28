FROM openjdk:8-jdk-alpine
EXPOSE 8085
ARG JAR_FILE=target/n11LoanApplication-docker.jar
ADD ${JAR_FILE} n11Loan.jar
ENTRYPOINT ["java","-jar","n11Loan.jar"]

