FROM openjdk:17
ADD barbershop.jar barbershop.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/barbershop.jar"]