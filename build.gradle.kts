plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://maven.aliyun.com/repository/public/")
    }
    maven {
        url = uri("https://maven.aliyun.com/repository/spring/")
    }
    mavenLocal()
    mavenCentral()
}

allprojects {
    repositories {
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
        mavenLocal()
        mavenCentral()
    }
}

dependencies {
    testImplementation("junit:junit:4.13.1")
}
