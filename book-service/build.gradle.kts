plugins {
  id("com.google.cloud.tools.jib")
  id("io.spring.dependency-management")
  id("org.springframework.boot")
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
  implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
  implementation("org.springframework.retry:spring-retry")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

jib {
  container {
    ports = listOf("9000")
  }
  to {
    image = "calebkiage/microservice-book-service"
  }
}
