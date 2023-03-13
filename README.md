
# Delivery app with microservice achitecture and event-driven design




## Tech Stack


**Server:** Java 17, Spring Boot, Liquibase, Docker, PostgreSQL, RabbitMQ, Swagger, Feign client, WebFlux Spring Security

**Tests:** Spock (Groovy), EasyRandom




## Run Locally

Clone the project

```bash
  git clone https://github.com/EvgeniyZhukovets/delivery-parcels
```

Go to the project directory

```bash
  cd delivery-parcels
```

Install dependencies

```bash
  mvn clean install
```

Start the servers throw docker-compose

```bash
  docker-compose up --build
```




## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Documentation

    Delivery, Auth, Order services have Swagger docs. Just open them through *host:port*/swagger-ui/index.html 

    Ports:
        Auth service: 8081
        Delivery service: 8082
        Order service: 8083
        Gateway service: 8084


## Authors

- [@EvgeniyZhukovets](https://github.com/EvgeniyZhukovets)

