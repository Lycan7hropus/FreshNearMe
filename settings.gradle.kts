plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "com.example.freshnearme"

include("modules")

include("modules:category")
findProject(":modules:category")?.name = "category"
include("modules:category:category-api")
findProject(":modules:category:category-api")?.name = "category-api"
include("modules:category:category-impl")
findProject(":modules:category:category-impl")?.name = "category-impl"

include("modules:offer")
findProject(":modules:offer")?.name = "offer"
include("modules:offer:offer-api")
findProject(":modules:offer:offer-api")?.name = "offer-api"
include("modules:offer:offer-impl")
findProject(":modules:offer:offer-impl")?.name = "offer-impl"

include("modules:user")
findProject(":modules:user")?.name = "user"
include("modules:user:user-api")
findProject(":modules:user:user-api")?.name = "user-api"
include("modules:user:user-impl")
findProject(":modules:user:user-impl")?.name = "user-impl"

include("modules:server")
findProject(":modules:server")?.name = "server"

include("modules:common")
findProject(":modules:common")?.name = "common"
