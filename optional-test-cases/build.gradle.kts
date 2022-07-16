plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.13.3")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}