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

gradle clean build

Above command will build the artifact.


gradle bootRun

To run the application locally, abpve command can deploy it on local Tomcat and it can be tested with following URL.
