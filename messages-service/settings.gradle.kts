rootProject.name = "messages-service"

includeBuild("../common")
include(
    "adapters:database",
    "adapters:controllers",
    "config:spring-config",
    "domain:application",
    "domain:entities",
    "drivers:spring-web"
)
