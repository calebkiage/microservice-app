import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
  id("org.springframework.boot") version "2.2.5.RELEASE" apply false
  id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
  kotlin("jvm") version "1.3.61" apply false
  kotlin("plugin.spring") version "1.3.61" apply false
  id("com.google.cloud.tools.jib") version "2.1.0" apply false
  id("io.gitlab.arturbosch.detekt") version "1.7.3" apply false
}

extra["springCloudVersion"] = "Hoxton.SR3"

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

subprojects {
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "io.gitlab.arturbosch.detekt")

  repositories {
    jcenter()
    mavenCentral()
  }

  the<DependencyManagementExtension>().apply {
    imports {
      mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
  }

  dependencies {
    "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.7.3")
  }
}

tasks.withType<Wrapper> {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.3"
}
