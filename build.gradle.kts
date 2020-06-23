plugins {
    java // java is already in the classpath by default in gradle, apply false is a no-op
    id("io.freefair.lombok") version "5.1.0" apply false
}

subprojects {
    
    group = "com.gakshintala.parkmycar"
    version = "0.0.1-SNAPSHOT"

    repositories {
        jcenter()
    }

    apply {
        plugin("java")
        plugin("io.freefair.lombok")
    }
    
    dependencies {
        val testImplementation by configurations
        
        testImplementation("org.junit.jupiter:junit-jupiter:+")
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


