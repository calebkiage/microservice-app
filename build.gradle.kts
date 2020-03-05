import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import com.google.cloud.tools.jib.gradle.JibExtension

plugins {
    id("org.springframework.boot") version "2.2.5.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
    kotlin("jvm") version "1.3.61" apply false
    kotlin("plugin.spring") version "1.3.61" apply false
    id("com.google.cloud.tools.jib") version "2.1.0" apply false
}

extra["springCloudVersion"] = "Hoxton.SR1"

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

subprojects {
    apply(plugin = "io.spring.dependency-management")

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }
}

val webProjects = listOf(project(":book-service"))

configure(webProjects) {
  apply(plugin = "com.google.cloud.tools.jib")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "java-library")

  configurations {
    "implementation" {
      exclude("org.springframework.boot", "spring-boot-starter-tomcat")
      exclude("org.springframework.cloud", "spring-cloud-starter-netflix-ribbon")
    }
  }

  dependencies {
    "implementation"("org.springframework.boot:spring-boot-starter-undertow")
  }

  configure<JibExtension> {
    container {
      labels = mapOf(Pair("MAINTAINER", "Caleb Kiage <caleb.kiage@gmail.com>"))
    }
  }
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "6.2.1"
}