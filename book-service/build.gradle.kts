import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("io.gitlab.arturbosch.detekt") version "1.7.3"
  id("io.spring.dependency-management") version "1.0.10.RELEASE"
  id("org.springframework.boot") version "2.4.0-RC1"
  kotlin("jvm") version "1.4.10"
  kotlin("plugin.spring") version "1.4.10"
}

extra["springCloudVersion"] = "2020.0.0-SNAPSHOT"

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

repositories {
  jcenter()
  mavenCentral()
  maven { url = uri("https://repo.spring.io/milestone") }
  maven { url = uri("https://repo.spring.io/snapshot") }
}

val javaVersion by extra { JavaVersion.VERSION_11 }

dependencyManagement {
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

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "$javaVersion"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<Wrapper> {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.7"
}
