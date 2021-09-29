import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    `java-gradle-plugin`
    `maven-publish`
}

group = "org.tribot.begtribot"
version = "1.0.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.lingala.zip4j:zip4j:2.6.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

gradlePlugin {
    plugins {
        create("TribotPlugin") {
            id = "org.tribot.begtribot.tribot-gradle-plugin"
            implementationClass = "org.tribot.begtribot.gradle.plugin.TribotPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/BegTribot/tribot-gradle-plugin")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("github.packages.user")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("github.packages.publishtoken")
            }
        }
    }
}
