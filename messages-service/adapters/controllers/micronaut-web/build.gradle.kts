plugins {
  kotlin("jvm")
  kotlin("kapt")
  kotlin("plugin.allopen")

  id("io.micronaut.application") version "1.0.5"
}

group = "com.example.microservice.messages.adapters.controllers"

micronaut {
  runtime.set(io.micronaut.gradle.MicronautRuntime.NETTY) // Using the Netty runtime
  testRuntime.set(io.micronaut.gradle.MicronautTestRuntime.JUNIT_5)
  processing {
    incremental.set(true)
  }
}

dependencies {
  implementation(project(":adapters:gateways:data"))
  implementation(project(":core:application"))
  implementation("com.example.microservice:common")
  implementation("io.micronaut:micronaut-http-client")
  implementation("io.micronaut:micronaut-runtime")
  implementation("io.micronaut:micronaut-validation")
  implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")

  runtimeOnly("ch.qos.logback:logback-classic")
  runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
}

configure<ApplicationPluginConvention> {
  mainClassName = "com.example.microservice.messages.adapters.controllers.web.MessagesServiceApplicationKt"
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "11"
  }
}
