plugins {
  id("com.google.cloud.tools.jib")
  id("org.springframework.boot")

  kotlin("plugin.spring")
}

group = "com.example.microservice.messages.adapters.controllers"

val developmentOnly: Configuration by configurations.creating

configurations {
  implementation {
    exclude("org.springframework.cloud", "spring-cloud-starter-netflix-ribbon")
  }

  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
}

dependencies {
  developmentOnly("org.springframework.boot:spring-boot-devtools")

  implementation(project(":adapters:gateways:data"))
  implementation(project(":core:application"))
  implementation("com.example.microservice:common")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.hibernate.validator:hibernate-validator")
  implementation("org.javers:javers-core")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
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

jib {
  container {
    creationTime = "USE_CURRENT_TIMESTAMP"
    labels = mapOf(Pair("MAINTAINER", "Caleb Kiage <caleb.kiage@gmail.com"))
    jvmFlags = emptyList()
    ports = listOf("9100")
  }
  to {
    image = "calebkiage/microservice-messages-service"
  }
}
