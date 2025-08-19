plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
    id("signing")
}

android {
    namespace = "gurux.common"
    compileSdk = 36

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    // Optional: for javadoc suppression
    tasks.withType<Javadoc>().configureEach {
        isFailOnError = false
        (options as? StandardJavadocDocletOptions)?.addStringOption("Xdoclint:none", "-quiet")
    }
}

// Rename Common-release.aar to gurux.common.android-<version>.aar
val versionName = project.version.toString()

val renameAar by tasks.registering(Copy::class) {
    val inputAar = layout.buildDirectory.file("outputs/aar/Common-release.aar")
    from(inputAar)
    into(inputAar.get().asFile.parentFile)
    rename { "gurux.common.android-$versionName.aar" }

    // Delete the original AAR after copying
    doLast {
        inputAar.get().asFile.delete()
    }
}

tasks.matching { it.name == "assembleRelease" }.configureEach {
    finalizedBy(renameAar)
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    archiveBaseName.set("gurux.common.android")
    from(android.sourceSets["main"].java.srcDirs)
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    archiveBaseName.set("gurux.common.android")
    archiveVersion.set(project.version.toString())
    from("README.md")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "org.gurux"
                artifactId = "gurux.common.android"
                version = project.version.toString()
                pom {
                    name.set("gurux.common.android")
                    description.set("gurux.common.android package implements interfaces that are needed for Gurux Media components and Gurux Device Framework. Purpose of Gurux Device Framework is help you to read your devices, meters and sensors easier")
                    url.set("https://www.gurux.fi")
                    licenses {
                        license {
                            name.set("GNU General Public License, version 2")
                            url.set("http://www.gnu.org/licenses/gpl-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("gurux")
                            name.set("Gurux ltd")
                            email.set("gurux@gurux.fi")
                        }
                    }

                    scm {
                        connection.set("scm:git:https://github.com/gurux/gurux.common.android.git")
                        developerConnection.set("scm:git:https://github.com/gurux/gurux.common.android.git")
                        url.set("https://github.com/gurux/gurux.common.android")
                    }
                }
            }
        }
    }
}
