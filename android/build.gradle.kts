import com.github.triplet.gradle.androidpublisher.ReleaseStatus
import com.github.triplet.gradle.androidpublisher.ResolutionStrategy
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

val localProperties = Properties().apply {
    rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}

fun localProperty(key: String): String =
    localProperties.getProperty(key) ?: System.getenv(key) ?: ""

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.playPublish)
}

android {
    namespace = "com.hellocuriosity.drappula"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.hellocuriosity.drappula"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = System.getenv("GITHUB_RUN_NUMBER")?.toInt() ?: 1
        versionName = System.getenv("VERSION") ?: "local"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "SLACK_BOT_TOKEN", "\"${localProperty("SLACK_BOT_TOKEN")}\"")
        buildConfigField("String", "SLACK_CHANNEL_ID", "\"${localProperty("SLACK_CHANNEL_ID")}\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("keystore/drappula.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = "drappula"
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    play {
        serviceAccountCredentials.set(rootProject.file("play-publish-credentials.json"))
        releaseStatus.set(ReleaseStatus.COMPLETED)
        resolutionStrategy.set(ResolutionStrategy.IGNORE)
        defaultToAppBundles.set(true)

        enabled.set(true)
        track.set("beta")
        artifactDir.set(file("build/outputs/bundle/productionRelease"))
    }

    flavorDimensions.addAll(listOf("all"))
    productFlavors {
        create("production") {
            dimension = "all"
        }
        create("staging") {
            dimension = "all"
            versionNameSuffix = "-staging"
            applicationIdSuffix = ".staging"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_19)
        }
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(project(":shared"))

    implementation(libs.androidx.annotation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.saveable)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.lifecycle.common)
    testImplementation(libs.androidx.lifecycle.viewmodel)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.testExt.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.core)
    testImplementation(libs.mockk.dsl)
    testImplementation(libs.robolectric)
    testImplementation(libs.robolectric.annotations)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.compose.material.icons.core)
    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.androidx.compose.ui.test.junit4)
    debugRuntimeOnly(libs.androidx.compose.ui.test.manifest)

    androidTestRuntimeOnly(libs.androidx.test.core)
    androidTestRuntimeOnly(libs.androidx.test.runner)
    androidTestRuntimeOnly(libs.junit)
}
