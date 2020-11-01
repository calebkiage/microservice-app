group = "com.example.microservice.messages.adapters.gateways"

dependencies {
  implementation(project(":core:application"))
  implementation("com.example.microservice:common")
  implementation("org.javers:javers-core:5.13.2")
  implementation("org.springframework:spring-jdbc:5.3.0")
}
