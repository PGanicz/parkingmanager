# Parking Manger project


This is a Spring Framework web application for managing the city parking spaces.

## Assumptions:

   * Every parking fee is calculated and stored when the driver escapes parking.
   * Every driver is treated as regular client unless he has a VIP account.

###All user stories are realized by following REST api:

###### New Ticket Creation
    POST /ticket
###### Payment of parking fee
    POST /fee
###### Checking how much has to be paid for ticket
    GET /fee
###### Total earnings at day
    GET /earnings?date={date in format dd-MM-yyyy}
###### Checking if parked vehicle is registered.
    GET /state

 ## Usage
 ### Requires
  * Java 8
  * Spring framework

 ### Setup
   To make it easy to run the test and it requirements, the ``start.sh`` script is provided. Which will start a:

  *  Gradle build
  *  Gradle unit test
  *  Run the Http server
  
 ### Build
 
    ./gradlew build
    
   This automatically downloads Gradle and builds the project, including running the tests.
   