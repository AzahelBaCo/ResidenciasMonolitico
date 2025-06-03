FROM eclipse-temurin:21.0.7_6-jdk

EXPOSE 3030

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

RUN ./mvnw dependency:go-offline

COPY ./src /root/srcclear


RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java","-jar","/root/target/spring-residencias-imagen.jar"]
