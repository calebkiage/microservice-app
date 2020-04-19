plugins {
  application
  id("com.google.cloud.tools.jib")

  kotlin("kapt")
  kotlin("plugin.allopen")
}

group = "com.example.microservice.messages.adapters.controllers"

allOpen {
  annotation("io.micronaut.aop.Around")
}

val developmentOnly: Configuration by configurations.creating
val micronautVersion: String by project

configurations {
  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
}

dependencies {
  implementation(kotlin("reflect"))
  implementation(kotlin("stdlib-jdk8"))
  implementation(project(":adapters:gateways:data"))
  implementation(project(":core:application"))
  implementation(platform("io.micronaut:micronaut-bom:$micronautVersion"))
  implementation("com.example.microservice:common")
  implementation("io.micronaut:micronaut-runtime")
  implementation("io.micronaut:micronaut-http-server-netty")
  implementation("io.micronaut:micronaut-http-client")

  kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
  kapt("io.micronaut:micronaut-inject-java")
  kapt("io.micronaut:micronaut-validation")

  runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
  runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

  testImplementation("io.micronaut.test:micronaut-test-kotlintest")
  testImplementation("io.mockk:mockk:1.9.3")
  testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
}

jib {
  container {
    creationTime = "USE_CURRENT_TIMESTAMP"
    labels = mapOf(Pair("MAINTAINER", "Caleb Kiage <caleb.kiage@gmail.com"))
    jvmFlags = emptyList()
    ports = listOf("9100")
  }
  to {
    image = "calebkiage/microservice-messages-service"
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    javaParameters = true
  }
}

tasks.withType<JavaExec> {
  classpath += developmentOnly
  jvmArgs("-noverify", "-XX:TieredStopAtLevel=1", "-Dcom.sun.management.jmxremote")
}
