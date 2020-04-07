import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  application
  id("com.google.cloud.tools.jib")

  kotlin("jvm")
  kotlin("plugin.spring")
}

group = "com.example.microservice.messages.adapters.presenters"

application {
  mainClassName = "com.example.microservice.messages.adapters.presenters.web.MessagesServiceApplicationKt"
}

val developmentOnly by configurations.creating

configurations {
  implementation {
    exclude("org.springframework.cloud", "spring-cloud-starter-netflix-ribbon")
  }

  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
  developmentOnly("org.springframework.boot:spring-boot-devtools")

  implementation(project(":adapters:gateways:data"))
  implementation(project(":core:application"))
  implementation(project(":core:domain"))
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

tasks.test {
  useJUnitPlatform()
  testLogging {
    events("passed", "skipped", "failed")
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}
