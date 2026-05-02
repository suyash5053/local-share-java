plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

extra["springCloudVersion"] = "2023.0.3"

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}