package dependencies

/**
 * Created at Tito on 3/13/19
 *
 * Version that will be used in Dependencies kotlin file.
 */

@Suppress("unused")
object Versions {

    const val versionName = "1.0"
    const val versionCode = 1
    const val androidGradlePlugin = "3.3.1"

    object SupportAndroidLibs {
        const val servicesPlugin = "3.2.1"
        const val compileSdk = 28
        const val minSdk = 21
        const val targetSdk = 28
    }

    object AndroidX {
        const val main = "1.0.0"
        const val material = "1.0.0"
        const val multiDex = "2.0.0"
        const val constraintLayout = "1.1.3"
        const val androidArc = "2.0.0"
    }

    object Testing {
        const val mockito = "2.10.0"
        const val espresso = "3.1.2-alpha01"
        const val runner = "1.1.2-alpha01"
        const val junit = "4.12"
        const val junitPlatform = "1.0.0"
    }

    object Kotlin {
        const val std = "1.3.21"
    }

    object Google {
        const val playServices = "12.0.1"
        const val firebase = "12.0.1"
        const val dagger = "2.16"
    }

    object RX {
        const val rxAndroid = "2.0.1"
        const val rxJava = "2.1.9"
        const val rxRelay = "2.0.0"
        const val rxIdler = "0.9.0"
    }

    object Retrofit {
        const val retrofit = "2.3.0"
        const val okHttp = "3.11.0"
    }

    const val OK_LOG = "2.3.0"

    const val MOSHI = "1.4.0"

    const val BUTTER_KNIFE = "9.0.0-SNAPSHOT"

    const val LEAK_CANARY = "1.5.4"

    const val GLIDE = "4.8.0"

}