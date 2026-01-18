import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.kotlin.dsl.withType

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kover)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jmailen.kotlinter")
    // Kover doesn't support com.android.kotlin.multiplatform.library plugin yet
    // Upstream: https://github.com/Kotlin/kotlinx-kover/issues/772
    // Tracking: https://github.com/hopeman15/drappula/issues/11
    if (name != "shared") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }

    detekt {
        config.setFrom("$rootDir/detekt/detekt.yml")
        buildUponDefaultConfig = true
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "19"
        reports {
            xml.required.set(false)
            html.required.set(true)
            txt.required.set(false)
            sarif.required.set(false)
            md.required.set(false)
        }
        exclude("**/resources/**")
        exclude("**/build/**")
    }
}

dependencies {
    kover(project(":android"))
    // TODO: Re-enable once Kover supports com.android.kotlin.multiplatform.library
    // https://github.com/hopeman15/drappula/issues/11
    // kover(project(":shared"))
}
