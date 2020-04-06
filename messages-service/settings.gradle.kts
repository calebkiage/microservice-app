rootProject.name = "messages-service"

includeBuild("../common")
include(
    "adapters:database",
    "adapters:controllers",
    "application",
    "config:spring-config",
    "entities",
    "drivers:spring"
)
