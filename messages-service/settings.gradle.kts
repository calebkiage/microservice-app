rootProject.name = "messages-service"

includeBuild("../common")
include(
    ":adapters:controllers:micronaut-web",
    ":adapters:controllers:spring-web",
    ":adapters:gateways:data",
    ":core:application",
    ":core:domain"
)
