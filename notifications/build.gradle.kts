import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.21" apply false
}

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")

  group = "com.example.microservice.messages"

  repositories {
    mavenCentral()
  }

  val javaVersion by extra { JavaVersion.VERSION_11 }

  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
  }

  tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
      events("passed", "skipped", "failed")
    }
  }
}

tasks.withType<Wrapper> {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.7"
}
