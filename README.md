# Spring Graphql GraalVM Samples

Spring Graphql GraalVM Samples

<img src="https://github.com/susimsek/spring-boot-graphql-graalvm-samples/blob/main/images/introduction.png" alt="Spring Graphql GraalVM Samples" width="100%" height="100%"/> 

# Graphql

GraphQL is a query language and server-side runtime for application programming interfaces (APIs) that prioritizes
giving clients exactly the data they request and no more.
GraphQL is designed to make APIs fast, flexible, and developer-friendly.
It can even be deployed within an integrated development environment (IDE) known as GraphiQL.
As an alternative to REST, GraphQL lets developers construct requests that pull data from multiple data sources in a
single API call.

## Prerequisites

* Java 17
* Kotlin
* Maven 3.x


## Build

You can install the dependencies and build by typing the following command

```sh
mvn clean install
```

To compile as native, run the following goal:

```
mvn native:compile -Pnative
```

Then, you can run the app as follows:

```
target/spring-boot-graphql-graalvm-samples
```

## Testing

You can run application's tests by typing the following command

```
mvn verify
```

## Docker

You can also fully dockerize  the sample applications. To achieve this, first build a docker image of your app.
The docker image of sample app can be built as follows:


```sh
mvn -Pnative spring-boot:build-image
```

to build native image
```sh
mvn -Pnative spring-boot:build-image
```