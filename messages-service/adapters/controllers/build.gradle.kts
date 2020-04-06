import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("kapt")
}

group = "com.example.microservice.messages.adapters"

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(project(":application"))
  implementation(project(":entities"))
  implementation("com.example.microservice:common")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.mapstruct:mapstruct")
  implementation("javax.validation:validation-api")

  kapt("org.mapstruct:mapstruct-processor")

  testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
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
