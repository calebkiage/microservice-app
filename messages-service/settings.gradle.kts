pluginManagement {
  repositories {
    maven { url = uri("https://repo.spring.io/milestone") }
    gradlePluginPortal()
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "org.springframework.boot") {
        useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
      }
    }
  }
}

rootProject.name = "messages-service"

includeBuild("../common")
include(
    ":adapters:controllers:micronaut-web",
    ":adapters:controllers:spring-web",
    ":adapters:gateways:data",
    ":core:application",
    ":core:domain"
)
