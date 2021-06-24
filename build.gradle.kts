import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "me.mariusz_krotos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("commons-cli:commons-cli:1.4")
    api("javax.mail:javax.mail-api:1.6.2")
    api("com.sun.mail:javax.mail:1.6.2")

    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "com.krotos.MainKt"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.krotos.MainKt"
    }
    from(configurations.compileClasspath.get().map { if (it.isDirectory()) it else zipTree(it) })
}