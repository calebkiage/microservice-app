import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.google.cloud.tools.jib")

  kotlin("jvm")
  kotlin("plugin.spring")
}

group = "com.example.microservice.messages.drivers"

configurations {
  implementation {
    exclude("org.springframework.cloud", "spring-cloud-starter-netflix-ribbon")
  }
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(project(":config:spring-config"))
  implementation(project(":adapters:controllers"))
  implementation("com.example.microservice:common")
  implementation("org.hibernate.validator:hibernate-validator")
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
