# Demo Store API


### Project Guide
This project runs by default in the port 8080. to change it create another profile or change in the application.properties.

To run the project:
You must have Maven at least 3.0.0
Java 8
Docker & Docker-Compose


Running the project:
mvn clean install
docker-compose up



### Initialize Database
POST for http://localhost:8080/product/create
POST for http://localhost:8080/discount/create    

### Test Request
POST '{ "items" :[{"productId": "1","quantity": "3"},{"productId": "2","quantity": "3"},{"productId": "3","quantity": "1"}]}' for http://localhost:8080/checkout/checkout

