plugins {
    `kotlin-dsl`
}

group = "com.mmd.simplenoteapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "simplenoteapp.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "simplenoteapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "simplenoteapp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "simplenoteapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "simplenoteapp.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
      register("androidHilt") {
        id = "simplenoteapp.android.hilt"
        implementationClass = "AndroidHiltConventionPlugin"
      }
        register("spotless") {
            id = "simplenoteapp.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}
