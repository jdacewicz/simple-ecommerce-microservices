
# Simple Ecommerce Microservices

A server-side ecommerce application built with microservices architecture, featuring product browsing, cart management, order processing, centralized configuration and service discovery via api gateway.


## Tech Stack

Java 21, Spring Boot 3, Maven, REST, PostgreSQL, MongoDB, Redis, Docker, Resilience4j


## Lessons Learned

Through this project, I learned how to design and build a complete microservices architecture, focusing on effective communication and integration between independently deployed applications. I gained experience working with different types of databases, ensuring that each service could manage its own data while still contributing to the overall functionality of the system. I also learned how to containerize each part of the application and orchestrate them using Docker Compose, allowing the entire system to run smoothly in a consistent and reproducible environment. This project helped me understand the practical challenges of distributed systems and how to approach them in a clean and scalable way.


## Installation

Clone the repository
```cli
git clone https://github.com/jdacewicz/simple-ecommerce-microservices
cd your-project
```

Start the services
```cli
docker-compose up --build
```
## Authors

- [@jdacewicz](https://www.github.com/jdacewicz)

