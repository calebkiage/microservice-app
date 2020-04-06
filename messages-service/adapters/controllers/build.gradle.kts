import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
}

group = "com.example.microservice.messages.adapters"

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(project(":domain:application"))
  implementation(project(":domain:entities"))
  implementation("com.example.microservice:common")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("javax.validation:validation-api")

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
