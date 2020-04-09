group = "com.example.microservice.messages.adapters.gateways"

dependencies {
  implementation(project(":core:application"))
  implementation("com.example.microservice:common")
  implementation("org.javers:javers-core")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework:spring-jdbc")
}
