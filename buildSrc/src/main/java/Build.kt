package dependencies

object Build {
    const val build_tools = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.3.2.0"
    const val safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_components}"
}