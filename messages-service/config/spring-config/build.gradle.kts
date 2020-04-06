import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
}

group = "com.example.microservice.messages.config"

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(project(":adapters:database"))
  implementation(project(":adapters:controllers"))
  implementation(project(":domain:application"))
  implementation(project(":domain:entities"))
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework:spring-context")
  implementation("org.springframework:spring-jdbc")
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    events("passed", "skipped", "failed")
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}
