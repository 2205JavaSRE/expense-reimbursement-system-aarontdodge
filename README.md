# Employee Reimbursement

## Description

   To build an Expense Reimbursement API, using Javalin, that is deployed on a remote server and ochestrate the application(s) using Docker and Docker Compose. Application monitoring will also be required to check the state of the server and the application. 

   ### Expense Reimbursement Application requirements:

   * The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. 
   
   * All employees in the company can log in and submit requests for reimbursement and view their past tickets and pending requests. Reimbursement tickets fall into 4 categoires, LODGING, TRAVEL, FOOD, or OTHER. 
   
   * Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement. 

   The requirements for the application itself is left open ended to let you decide how to best implement the features. You are also allowed to create your own project (with a similar level of technical difficulty) but you must first reach out to your manager for approval. 

## Purpose

   We want to see that you can build an API using the technologies that have been taught so far in the program. In addition to that, we want to see that you are able to deploy the application onto a remote server in a containerized environment. 

## Technical Requirements

1. Functionality should reflect the below user stories.
2. Data is stored in a database.
3. Data Access is performed through the use of JDBC in a data layer consisting of Data Access Objects.
4. The API is REST based, following as many of the RESTful constraints as appropriate
5. The application is containerized and running on a docker compose network
6. Applications and systems are monitored using Prometheus and Grafana 

NOTE: Include any configuration files in this repository, make sure to avoid adding private information to this repository as it will be public.

## Technologies

1. Java 8
2. Javalin (Jetty Server)
3. JDBC
2. PostgresSQL
3. Docker 
4. Docker Compose 
5. Prometheus 
6. Grafana 
7. Any 3rd Party Plugins as required