import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.cocoapods)
}

android {
    namespace = "com.gbmultiplatform.ios"
    compileSdk = 35
}

kotlin {
    val componentVersionName: String by rootProject.properties
    val iosDeploymentTarget: String by rootProject.properties
    val iosBaseName: String by rootProject.properties

    androidTarget {
        compilerOptions.jvmTarget.set(JvmTarget.fromTarget(
            JavaVersion.VERSION_17.toString()
        ))
    }

    val xcf = XCFramework(iosBaseName)
    val targets = listOf(iosX64(), iosArm64(), iosSimulatorArm64())

    targets.forEach {
        it.binaries.framework {
            baseName = iosBaseName
            binaryOption("bundleShortVersionString", componentVersionName)
            xcf.add(this)
        }
    }

    cocoapods {
        name = iosBaseName
        version = componentVersionName
        summary = "GBMultiplatform iOS entry point"
        homepage = "https://www.sergiogalera.dev/"
        authors = "sgale.developer@gmail.com"
        ios.deploymentTarget = iosDeploymentTarget
        framework {
            baseName = iosBaseName
            isStatic = true
        }
    }
}


dependencies {
    commonMainImplementation(compose.components.resources)
    commonMainImplementation(projects.core.di)
}

compose.resources {
    generateResClass = always
    packageOfResClass = "com.gbmultiplatform.ios"
}
