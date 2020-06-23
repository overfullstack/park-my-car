plugins {
    java
    id("io.freefair.lombok") version "5.1.0" apply false
    id("com.adarshr.test-logger") version "2.1.0"
}

subprojects {
    
    group = "com.gakshintala.parkmycar"
    version = "0.0.1-SNAPSHOT"

    repositories {
        jcenter()
    }

    apply {
        plugin("java")
        plugin("com.adarshr.test-logger")
        plugin("io.freefair.lombok")
    }
    
    dependencies {
        val testImplementation by configurations
        val testRuntimeOnly by configurations
        
        testImplementation("org.junit.jupiter:junit-jupiter-api:+")
        testImplementation("org.junit.jupiter:junit-jupiter-params:+")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:+")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_14
        targetCompatibility = JavaVersion.VERSION_14
    }

    tasks.withType<Test> {
        useJUnitPlatform {
            excludeEngines("junit-vintage")
        }
        jvmArgs("--enable-preview")
    }
}

testlogger {
    setTheme("mocha")
    showExceptions = true
    showStackTraces = true
    showFullStackTraces = true
    showCauses = true
    slowThreshold = 2000
    showSummary = true
    showSimpleNames = true
    showPassed = true
    showSkipped = true
    showFailed = true
    showStandardStreams = true
    showPassedStandardStreams = true
    showSkippedStandardStreams = true
    showFailedStandardStreams = true
}


