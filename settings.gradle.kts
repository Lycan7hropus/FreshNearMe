plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "com.example.freshnearme"

include("modules")


include("modules:chat-service")
findProject(":modules:chat-service")?.name = "chat-service"

include("modules:shared")
findProject(":modules:shared")?.name = "shared"

include("modules:backend-api")

include("modules:backend-api:category")
findProject(":modules:backend-api:category")?.name = "category"
include("modules:backend-api:category:category-api")
findProject(":modules:backend-api:category:category-api")?.name = "category-api"
include("modules:backend-api:category:category-impl")
findProject(":modules:backend-api:category:category-impl")?.name = "category-impl"

include("modules:backend-api:offer")
findProject(":modules:backend-api:offer")?.name = "offer"
include("modules:backend-api:offer:offer-api")
findProject(":modules:backend-api:offer:offer-api")?.name = "offer-api"
include("modules:backend-api:offer:offer-impl")
findProject(":modules:backend-api:offer:offer-impl")?.name = "offer-impl"

include("modules:backend-api:user")
findProject(":modules:backend-api:user")?.name = "user"
include("modules:backend-api:user:user-api")
findProject(":modules:backend-api:user:user-api")?.name = "user-api"
include("modules:backend-api:user:user-impl")
findProject(":modules:backend-api:user:user-impl")?.name = "user-impl"

include("modules:backend-api:server")
findProject(":modules:backend-api:server")?.name = "server"

include("modules:backend-api:common")
findProject(":modules:backend-api:common")?.name = "common"
