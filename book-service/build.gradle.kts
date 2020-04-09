import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.google.cloud.tools.jib") version "2.1.0"
  id("io.gitlab.arturbosch.detekt") version "1.7.3"
  id("io.spring.dependency-management") version "1.0.9.RELEASE"
  id("org.springframework.boot") version "2.2.5.RELEASE"
  kotlin("jvm") version "1.3.61"
  kotlin("plugin.spring") version "1.3.61"
}

extra["springCloudVersion"] = "Hoxton.SR3"

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

repositories {
  jcenter()
  mavenCentral()
}

configurations {
  implementation {
    exclude("org.springframework.cloud", "spring-cloud-starter-netflix-ribbon")
  }
}

the<DependencyManagementExtension>().apply {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

dependencies {
  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.7.3")

  implementation("com.example.microservice:common")
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

jib {
  container {
    creationTime = "USE_CURRENT_TIMESTAMP"
    labels = mapOf(Pair("MAINTAINER", "Caleb Kiage <caleb.kiage@gmail.com>"))
    jvmFlags = emptyList()
    ports = listOf("9000")
  }
  to {
    image = "calebkiage/microservice-book-service"
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<Wrapper> {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.3"
}
