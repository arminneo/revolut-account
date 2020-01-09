# How to run
Please take it to consideration that it take a lot of time to make this application. And If I wanted to make it perfect it would require much more time.

[1] Install latest [JRE 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html) or higher or Make sure you have [OpenJDK](https://openjdk.java.net/install/)

[2] Ensure you have version 11 or higher. This project is based on Language level 9+
```` shell script
java --version
````
[3] Install and configure [maven](https://maven.apache.org/).

[4] Make sure you have Maven 3.6 or higher:
```` shell script
mvn -v
```` 
[5] Make sure you are in the application's directory.

[6] Run the application using following command:
```` shell script
mvn package
java -jar ./target/revolut-account-1.0-SNAPSHOT.jar 
```` 

[*] To test the application
```` shell script
mvn test
````

## notes
Application running on TCP PORT 4000 make sure it is open.

# Assumptions
- Just single currency
- No user management and no validation if user exists
- No proxy and caching
- No gateway

# Approaches
## Concurrency and race condition
I used blocking queue for payment and deposit. Synchronization block for thread safety. And transaction for data integrity.
## Test coverage
88% test coverage. Cannot be 100% there is generated code.
- Concurrency
- Covering all validations:
  - Insufficient funds
  - Account not found
  - Same source
  - Wrong input
  - ...

# API calling for test purpose
You can you use [Post Man 2.1+](https://www.getpostman.com/) there is PostMan collection file <a href="Revolut-Account.postman_collection.json">Revolut-Account.postman_collection.json</a>  
API root path: http://localhost:4000/api/v1

| HTTP Verb | URL | Description |
| --------- | --- | ----------- | 
| GET | /account/:code | Get an account and its transactions |
| POST | /account/create/:userId | Creates a new account with give userId
| POST | /account/transfer | Transfer an amount money from source to destination account | 
| POST | /account/deposit | Deposit an amount of money to source account |

Transfer body request content
```` json
{
	"source": "111111",
	"destination": "222222",
	"amount": 3
}
````

Deposit body request content
```` json
{
	"destination": "111111",
	"amount": 22
}
````

## Note
You can use `111111`, `222222`, `333333` as sample account code already seeded into system. 

# Tech Stack
- Java 9
- Spark Java (with Jetty)
- Jooq for database connection
- H2 in-memory database
 
# Expectations
- Thread-safety
- Big test coverage as you can also cover all the sad paths
- Simple and not over-engineered.