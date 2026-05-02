plugins {
    java
    id("org.springframework.boot") version "3.3.5" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
    group = "com.localshare"
    version = "1.0.0"
}

subprojects {
    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}