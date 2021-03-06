package com.asadmansoor.crumbs.buildsrc

object Build {
    const val compileSdkVersion = 29
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
}

object Libs {
    object Core {
        const val androidGradlePlugin = "com.android.tools.build:gradle:3.6.3"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
    }

    object View {
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val viewPager = "androidx.viewpager2:viewpager2:1.0.0"
    }

    object Kotlin {
        private const val version = "1.3.71"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object Lifecycle {
        private const val version = "2.2.0"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
    }

    object Coroutines {
        private const val version = "1.3.4"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Kodein {
        private const val version = "5.2.0"
        const val generic = "org.kodein.di:kodein-di-generic-jvm:$version"
        const val android = "org.kodein.di:kodein-di-framework-android-x:$version"
    }

    object Groupie {
        private const val version = "2.8.1"
        const val groupie = "com.xwray:groupie:$version"
        const val androidExtension = "com.xwray:groupie-kotlin-android-extensions:$version"
    }

    object Navigation {
        private const val version = "2.3.1"
        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    }

    object Room {
        private const val version = "2.2.5"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val roomKtx = "androidx.room:room-ktx:$version"
    }

    object MaterialDialog {
        const val input = "com.afollestad.material-dialogs:input:3.3.0"
        const val sheet = "com.afollestad.material-dialogs:bottomsheets:3.2.1"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }

    object Test {
        const val junit = "junit:junit:4.12"
        const val androidXJunit = "androidx.test.ext:junit:1.1.2"
        const val core = "androidx.arch.core:core-testing:2.1.0"
        const val coreKtx = "androidx.test:core-ktx:1.3.0"
        const val mockito = "org.mockito:mockito-inline:2.8.47"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        const val core = "androidx.arch.core:core-testing:2.1.0"
        const val room = "android.arch.persistence.room:testing:2.2.5"
        const val mockito = "org.mockito:mockito-core:3.2.4"
        const val dexMockito = "com.linkedin.dexmaker:dexmaker-mockito:2.12.1"
    }
}
