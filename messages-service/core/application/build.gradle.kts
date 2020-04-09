group = "com.example.microservice.messages.core"

dependencies {
  implementation(project(":core:domain"))
  implementation("com.example.microservice:common")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  testImplementation("io.mockk:mockk:1.9.kotlin12")
}
