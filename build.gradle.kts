import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.3.11"
    id("org.springframework.boot") version "2.1.2.RELEASE"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.11"
}

group = "fr.isima"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
}


buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.2.RELEASE")
    }
}

apply(plugin = "io.spring.dependency-management")
apply(plugin = "kotlin-spring")

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")

    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.10")

    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    testCompile("org.jetbrains.kotlin:kotlin-test")
    testCompile("org.jetbrains.kotlin:kotlin-test-junit")
    testCompile("io.kotlintest:kotlintest:2.0.7")

    compile("javax.inject:javax.inject:1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("javax.servlet:jstl")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper")

    testCompile("org.assertj:assertj-core:3.8.0")
    testCompile("org.mockito:mockito-core:2.13.0")
    testCompile("junit:junit:4.12")
}

//reload resources dinamycally
tasks.getByName<BootRun>("bootRun") {
    sourceResources(sourceSets["main"])
}

val javaVersion4Kotlin = "1.8"
tasks.getByName<KotlinCompile>("compileKotlin") {
    kotlinOptions.jvmTarget = javaVersion4Kotlin
}
tasks.getByName<KotlinCompile>("compileTestKotlin") {
    kotlinOptions.jvmTarget = javaVersion4Kotlin
}

val mainClass = "fr.isima.server.ServerKt"
tasks.getByName<BootJar>("bootJar") {
    mainClassName = mainClass
}

application {
    mainClassName = mainClass
}