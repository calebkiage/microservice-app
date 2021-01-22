plugins {
  id("io.spring.dependency-management") version "1.0.10.RELEASE"
  id("org.springframework.boot") version "2.4.1"

  kotlin("jvm")
  kotlin("plugin.spring")
}

group = "com.example.microservice.messages.adapters.controllers"

extra["springCloudVersion"] = "2020.0.0"

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

dependencies {
  implementation(project(":adapters:gateways:data"))
  implementation(project(":core:application"))
  implementation("com.example.microservice:common")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.hibernate.validator:hibernate-validator")
  implementation("org.javers:javers-core:5.13.2")
  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
  implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
  implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")

  runtimeOnly("com.h2database:h2")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
}
