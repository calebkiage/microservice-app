plugins {
  id("io.gitlab.arturbosch.detekt") version "1.7.3"
  kotlin("jvm") version "1.4.10"
}

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

repositories {
  jcenter()
  mavenCentral()
}

dependencies {
  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.7.3")
}

tasks.withType<Wrapper> {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.7"
}

