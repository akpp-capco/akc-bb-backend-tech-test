# Backbase Backend Technical Test
### OpenBank Transaction Integration

| Component | Version |
| --- | --- |
| Spring | 5.2.12 |
| Spring Security | 5.3.6 |
| Servlet Spec | 3.1.0 |
| Tomcat | 8.5.63 |

#### Build
This is a Maven project. It can be built with:
```shell script
mvn clean install
```
This will result in a WAR file that can be deployed to a server (i.e. Tomcat).

#### Run
This project has a maven plugin to quickly start a Tomcat 8 server that can be run with:
```shell script
mvn cargo:run
``` 
Started this way, the application can be accessed at <http://localhost:8080/akc-bb-backend-tech-test>.

#### Security
Spring Security has been set up with an in-memory credential store. 
One user has been pre-loaded. 

You may login with:
- **Username:** akc
- **Password:** P4ssw0rd

#### Transactions
Three RESTful API endpoints are available:
- Transactions list
  - GET `/transactions`
- Transaction filter based on transaction type
  - GET `/transactions/filter/{transactionType}`
- Total amount for transaction type
  - GET `/transactions/filter/{transactionType}/total`

All three are secured and can only be accessed by authenticated users (see Security). Anonymous users will be redirected to login.

It seems there is only one resource publicly available from the OpenBank API to test. 
The parameters for that resource have been hardcoded into this application. 
Therefore, the `/transaction` endpoints will always return this one resource, regardless of who is logged in.
Ideally, this would be parametrized based on the user's identity and further request parameters (i.e. for the account id).

**OpenBank Resource:** <https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions>

**Example URL:** <http://localhost:8080/akc-bb-backend-tech-test/transactions/filter/SEPA/total>

#### Tests
Unit tests implemented with JUnit. These can be executed with:
```shell script
mvn test
``` 

