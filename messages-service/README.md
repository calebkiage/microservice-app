# Microservice App

Practice microservice app development

## Architecture

The code follows the [clean architecture style](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

![Clean Architecture Representation](../docs/img/CleanArchitecture.jpg)
> Classes marked with `*<I>` are interfaces.
> Open arrowheads are using relationships. Closed arrowheads are implements or
> inheritance relationships. 

Below are the layers from outermost to the innermost

### Drivers & Frameworks
This layer doesn't have any code

### Interface Adapters
#### Gateways
##### Data (adapters:gateways:data)
Implements the storage ports e.g. MessageWriter. Depends on:
1. `:core:application`

#### Controllers
This layer contains MVC controllers. It also contains the `Main` component that sets up the IoC container. See BeansConfig

##### Spring Web (adapters:controllers:spring-web)
This is a spring boot web app that depends on:
1. `:adapters:gateways:database`
2. `:core:application`

#### Core
##### Application Business Rules (core:application)
This layer contains business rules/use cases. Depends on:
1. `:core:domain`

##### Enterprise Business Rules (core:domain)
Contains enterprise business rules. This is the inner-most layer and has no dependencies.

### Prerequisites

1. Docker
2. Docker Compose
3. JDK 8+

## Running the tests

```shell script
$ ./gradlew test
```

### Coding style tests

```shell script
$ ./gradlew detekt
```

## Deployment

Add additional notes about how to deploy this on a live system
