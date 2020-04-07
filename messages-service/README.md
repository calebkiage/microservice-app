# Microservice App

Practice microservice app development

## Architecture

The code follows the [clean architecture style](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

![Clean Architecture Representation](../docs/img/CleanArchitecture.jpg)
> Classes marked with \<I> are interfaces.
> Open arrowheads are using relationships. Closed arrowheads are implements or
> inheritance relationships. 

Below are the layers from outermost to the innermost

### Config
This layer glues everything together. It's like a Main component.

### Drivers & Frameworks
This layer doesn't have any code

### Interface Adapters
#### Controllers
Takes data from the code in outer layers and runs it through the use cases. Depends on:

1. `:application`
2. `:entities`

#### Presenters
##### Spring Config
Use Spring IoC to register component instances. Depends on:
1. `:adapters:database`
2. `:adapters:controllers`
3. `:application`
4. `:entities`

##### Spring
A spring boot app that depends on:
1. `:config:spring-config`
2. `:adapters:controllers`

#### Gateways
##### Database
Implementation of the storage ports from `:application`. Depends on:
1. `:application`

### Application Business Rules
This layer contains business rules/use cases. Depends on:
1. `:entities`

### Enterprise Business Rules
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
