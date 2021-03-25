# Cactus API Rest Client

This project contains a [Rest Client](https://github.com/muneeb706/cactus-rest-client/blob/main/src/main/java/com/example/cactusrestclient/CactusRestClient.java) for communicating with [Cactus API](https://cactus.nci.nih.gov/chemical/structure) to get given chemical identifier information in given representation.

## How To Run:

This is a Spring Boot application and there are many ways to run it. One way to run this application is to open this application as spring boot application in Eclipse or IntelliJ and run it using the built in commands provided by these IDE's. However, to run this application manually using command line / terminal. Follow these steps:

1. First install [Java Development Kit (Version >= 11)](https://www.oracle.com/java/technologies/javase-downloads.html) and add its installation path to environment.
2. Then install [Apache maven 9 (Version >= 3)](https://maven.apache.org/download.cgi) and add its installation path to environment.
3. Open the command line / terminal and verify access to mvn and java commands by typing java --version and mvn--version commands.
4. In the terminal go to root folder of the project.
5. Enter 'mvn clean' command, to clean the previous builds (if any).
6. Enter 'mvn install' command, to install the dependencies specified in pom.xml and to run the unit tests as well.
7. If everything goes well uptill now, then to run the spring boot application / server, enter 'mvn sprin-boot:run'.
8. At this point, application will be hosted in localhost:8080.
9. Following are the endpoints that are served by this application:
   1. localhost:8080/cactus/history
   2. localhost:8080/cactus/{identification}/{representation}
10. In case of any issue, you follow this [link](https://morioh.com/p/b8d8ab74dcab), for detailed steps.
