# digicart-ms

### Pre requisite
1. Setup a local Mysql db
2. In application.properties setup the config as per your local settings
spring.datasource.url= 
spring.datasource.username=
spring.datasource.password=

### Getting Started
1. Clone this repository
2. Run `mvn clean package`
3. Run `java -jar target/digicart-ms-0.0.1-SNAPSHOT.jar`
4. Do a get or post to the endpoint (/api/users)

GET  :  http://localhost:8080/api/users [Fetches list of users]
POST :  http://localhost:8080/api/users [Creates a new user]