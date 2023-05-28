# Exante Coding Challenge

## Verifit 
Verifit is a gym tracking application that manages members discounts by capturing attendance records and applying a discount when member goes at least once a week for 3 weeks in a row to get a streak.

## Details
Java 8 Spring Boot web application with an embedded Hibernate database( doesn't persist between runs ).
The application has a dockerfile for deploying in a container, see Dockerfile for details.

## Software used
- Java 8 / OpenJDK 1.8
- Docker v23.0.5

## Usage
Terminal commands to build and run the docker instance (-d flag for detached mode). The
`application.properties` file sets the server port of the application.
```shell
docker build --tag=verifit-server-1.0.0:latest .
...

docker run -d -p 8000:8000 verifit-server-1.0.0:latest
```

## Author
Joshua Hetherington

## License
[MIT](https://choosealicense.com/licenses/mit/)
