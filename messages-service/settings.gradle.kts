rootProject.name = "messages-service"

includeBuild("../common")
include(
    ":adapters:controllers:spring-web",
    ":adapters:gateways:data",
    ":core:application",
    ":core:domain"
)
