plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(project(":core"))
    implementation(project(":year2024"))
    implementation(project(":year2025"))
}

application {
    mainClass.set("com.kilehynn.aoc.app.MainKt")
}
