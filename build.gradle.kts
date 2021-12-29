import kotlinx.benchmark.gradle.benchmark
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.1"
    application
}

group = "io.github.guohao"
version = "1.0.0-SNAPSHOT"

configure<AllOpenExtension> {
    annotation("org.openjdk.jmh.annotations.State")
}


sourceSets.all {
    java.setSrcDirs(listOf("$name/src"))
    resources.setSrcDirs(listOf("$name/resources"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.dslplatform:dsl-json-java8:1.9.9")
    kapt("com.dslplatform:dsl-json-java8:1.9.9")
    implementation("cc.plural:jsonij:0.5.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.1")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
benchmark {
    configurations {
        named("main") {
            iterationTime = 5
            iterationTimeUnit = "sec"

        }
    }
    targets {
        register("main") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.21"
        }
    }
}