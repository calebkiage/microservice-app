import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.2.6.RELEASE" apply false
  id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
  kotlin("jvm") version "1.3.71" apply false
  kotlin("plugin.spring") version "1.3.71" apply false
  id("com.google.cloud.tools.jib") version "2.1.0" apply false
  id("io.gitlab.arturbosch.detekt") version "1.7.3" apply false
}

extra["springBootVersion"] = "2.2.6.RELEASE"
extra["springCloudVersion"] = "Hoxton.SR3"

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

subprojects {
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "io.gitlab.arturbosch.detekt")
  apply(plugin = "org.jetbrains.kotlin.jvm")

  group = "com.example.microservice.messages"

  repositories {
    jcenter()
    mavenCentral()
  }

  val javaVersion by extra { JavaVersion.VERSION_1_8 }
  val javersVersion by extra { "5.8.12" }
  val mapStructVersion by extra { "1.3.1.Final" }

  the<DependencyManagementExtension>().apply {
    imports {
      mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
      mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }

    dependencies {
      dependencySet(mapOf(Pair("group", "org.javers"), Pair("version", javersVersion))) {
        entry("javers-core")
      }

      dependencySet(mapOf(Pair("group", "org.mapstruct"), Pair("version", mapStructVersion))) {
        entry("mapstruct")
        entry("mapstruct-processor")
      }

      dependency("io.gitlab.arturbosch.detekt:detekt-formatting:1.7.3")
    }
  }

  dependencies {
    "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting")

    "testImplementation"("org.junit.jupiter:junit-jupiter")
  }

  tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
      events("passed", "skipped", "failed")
    }
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "$javaVersion"
    }
  }
}

tasks.withType<Wrapper> {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.3"
}
