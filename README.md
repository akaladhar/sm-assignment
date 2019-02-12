# SM-Assignment
This is Spring Boot based application with one REST end point. This end point takes parameters
mileage, mpg, fuel type and journey date(historic). Compares the current price of fuel with that of the journey date and produces a message if the the same journey today is going to be cheaper/costler/same cost.

 - Historic prices are downloaded and kept in the resources.
 - Hardcoded the current prices as it is not required to keep them updated.

## Technical Debt
- Historic and current prices to be stored in a database table.
- Current prices to be read from a database table/web service API if available.
- Add Sonar for checking code quality
- Add logger framework like log4j or similar ones.

## How to run

Clone source code from github using command

https://github.com/akaladhar/sm-assignment.git

Import project into IDE of your choice as a gradle project and run

`gradle clean build`

Above command will build the artifact and to run application locally, run

`gradle bootRun`

Above command can start a Tomcat instance and deploy application on local Tomcat and it can be tested with following URL.

http://localhost:8080/getSavings?date=10/06/2004&mpg=10&mileage=200&fuel=petrol
