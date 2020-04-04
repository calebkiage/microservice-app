import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.google.cloud.tools.jib")
  id("org.springframework.boot")

  kotlin("jvm")
  kotlin("plugin.spring")
}

configurations {
  implementation {
    exclude("org.springframework.cloud", "spring-cloud-starter-netflix-ribbon")
  }
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation("com.example.microservice:common")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
  implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
  implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
  implementation("org.springframework.kafka:spring-kafka")

  runtimeOnly("com.h2database:h2")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }

  testImplementation("org.springframework.kafka:spring-kafka-test")
}

jib {
  container {
    labels = mapOf(Pair("MAINTAINER", "Caleb Kiage <caleb.kiage@gmail.com>"))
    ports = listOf("9100")
  }
  to {
    image = "calebkiage/microservice-messages-service"
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}
