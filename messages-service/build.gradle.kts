import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.10" apply false
  kotlin("kapt") version "1.4.10" apply false
  kotlin("plugin.allopen") version "1.4.10" apply false
  id("io.gitlab.arturbosch.detekt") version "1.7.3" apply false
}

group = "com.example.microservice"
version = "0.0.1-SNAPSHOT"

subprojects {
  apply(plugin = "io.gitlab.arturbosch.detekt")
  apply(plugin = "org.jetbrains.kotlin.jvm")

  group = "com.example.microservice.messages"

  repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
  }

  val javaVersion by extra { JavaVersion.VERSION_11 }
  val javersVersion by extra { "5.8.12" }
  val mapStructVersion by extra { "1.3.1.Final" }

  dependencies {
    "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting")

    "testImplementation"("org.junit.jupiter:junit-jupiter:5.7.0")
  }

  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
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
  gradleVersion = "6.7"
}
