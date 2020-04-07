# Microservice App

Practice microservice app development

## Architecture

The code follows the [clean architecture style](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

![Clean Architecture Representation](../docs/img/CleanArchitecture.jpg)
> Classes marked with \<I> are interfaces.
> Open arrowheads are using relationships. Closed arrowheads are implements or
> inheritance relationships. 

Below are the layers from outermost to the innermost

### Drivers & Frameworks
This layer doesn't have any code

### Interface Adapters
#### Gateways
##### Data
Implements the storage ports e.g. MessageWriter. Depends on:
1. `:core:application`

#### Presenters
##### Spring Web
This layer glues everything together. It contains the `Main` component that sets up the IoC container.
It's a spring boot app that depends on:
1. `:adapters:gateways:database`
1. `:core:application`
2. `:core:domain`

#### Core
### Application Business Rules
This layer contains business rules/use cases. Depends on:
1. `:core:entities`

### Enterprise Business Rules (domain)
Contains enterprise business rules. This is the inner-most layer and has no dependencies.

### Prerequisites

1. Docker
2. Docker Compose
3. JDK 8 

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
