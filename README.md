# Exante Coding Challenge

## Verifit 
Verifit is a gym tracking application that manages members discounts by capturing attendance records and applying a discount when member goes at least once a week for 4 weeks or more in a row to get a streak.

## Details
Java 8 Spring Boot web application with an embedded Hibernate database( doesn't persist between runs ).
The application has a dockerfile for deploying in a container, see Dockerfile for details.

## Software used
- Java 8 / OpenJDK 1.8
- Docker v23.0.5
- Apache Maven v3.9.1

## Usage
Terminal commands to build and run the app & docker instance (-d flag for detached mode). `Note` the
`application.properties` file sets the server port of the application.
```shell
mvn clean package
...

docker build --tag=verifit-server-1.0.0:latest .
...

docker run -d -p 8000:8000 verifit-server-1.0.0:latest
```
## Testing
`Verifit Collection.postman_collection.json` file can be imported into your Postman tool for testing and confirmation of the applications REST API's.

## OpenAPI Definition
Swagger docs will be auto-generated for the project when run. Go to: `http://localhost:8000/swagger-ui.html` to view.

## Author
Joshua Hetherington

## License
[MIT](https://choosealicense.com/licenses/mit/)
