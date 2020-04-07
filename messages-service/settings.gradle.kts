rootProject.name = "messages-service"

includeBuild("../common")
include(
    "adapters:gateways:data",
    "adapters:presenters:spring-web",
    "core:application",
    "core:domain"
)
