plugins {
    application
}

application {
    mainClassName = "com.gakshintala.parkmycar.ParkMyCarApplication"
    applicationDefaultJvmArgs = listOf("--enable-preview")
}

val run: JavaExec by tasks
run.standardInput = System.`in`

dependencies {
    implementation(project(":core"))
    implementation(project(":adapters:exchange"))
    implementation(project(":adapters:persistence"))
}
