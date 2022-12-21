# NYC TLC Dataset Tool

This project was created using Spring Boot and the Vaadin Framework. 
It contains all the necessary configuration files, database files, and so on. The project is a standard Maven project, so dependencies can be found in a pom.xml file. 
The only requirement is Java 11.

## Running the application

Running the application is a 3 step process: 
1. Open a terminal and navigate to the 
2. Execute the following command depending on your OS: `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux). This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.
3. Once the JAR file is built, you can run it with `java -jar target/nyctlcdatasettool-1.0.jar`  
After running the application, open http://localhost:8080 in your browser to view it.
