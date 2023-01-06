buildscript {
    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.gms.googleServices) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    id("org.jetbrains.kotlinx.kover") version "0.6.1" apply true
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

koverMerged {
    enable()

    // common filters for all default Kover tasks
    filters {
        // common class filter for all default Kover merged tasks
        classes {

            // class inclusion rules
            includes += "com.mmd.simplenoteapp.*"

            // class exclusion rules
            excludes += listOf("com.mmd.simplenoteapp.core.*")
        }

        // common annotation filter for all default Kover merged tasks
        annotations {

            // exclude declarations marked by specified annotations
            excludes += listOf(
                "com.mmd.simplenoteapp.Annotation",
                "*Generated"
            )
        }

        // common projects filter for all default Kover merged tasks
        projects {

            // Specifies the projects excluded from the merged tasks
            excludes += listOf(
                ":core-model",
                ":core-navigation",
                ":core-authentication",
                ":core-data",
                ":core-designsystem",
                ":core-preferences"
            )
        }
    }

    htmlReport {
        onCheck.set(false)
        // change report directory
        reportDir.set(layout.buildDirectory.dir("my-project-report/html-result"))
        overrideClassFilter {
            // override class inclusion rules
            includes += "com.mmd.simplenoteapp.*"
            // override class exclusion rules
            excludes += listOf("com.mmd.simplenoteapp.core.*")
        }
        overrideAnnotationFilter {
            excludes += "*OverrideGenerated"
        }
    }
    xmlReport {
        onCheck.set(false)
        // change report directory
        reportFile.set(layout.buildDirectory.file("my-project-report/result.xml"))
        overrideClassFilter {
            // override class inclusion rules
            includes += "com.mmd.simplenoteapp.*"
            // override class exclusion rules
            excludes += listOf("com.mmd.simplenoteapp.core.*")
        }
        overrideAnnotationFilter {
            excludes += "*OverrideGenerated"
        }
    }
}
