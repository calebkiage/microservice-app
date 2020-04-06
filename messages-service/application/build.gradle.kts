import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("kapt")
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
}

val mapstructVersion = "1.3.1.Final"

dependencies {
  implementation(project(":entities"))
  implementation("com.example.microservice:common")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.mapstruct:mapstruct:${mapstructVersion}")

  kapt("org.mapstruct:mapstruct-processor:${mapstructVersion}")

  testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
  testImplementation("io.mockk:mockk:1.9.kotlin12")
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
