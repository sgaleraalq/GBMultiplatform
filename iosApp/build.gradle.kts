plugins {
    id("com.gbmultiplatform.ios.app")
}

dependencies {
    commonMainImplementation(compose.components.resources)
    commonMainImplementation(projects.core.di)
}

compose.resources {
    generateResClass = always
    packageOfResClass = "com.gbmultiplatform.ios"
}
