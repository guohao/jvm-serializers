/*
 * Copyright 2022 guohaoice@gmail.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import kotlinx.benchmark.gradle.benchmark
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.1"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

group = "io.github.guohao"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
configure<AllOpenExtension> {
    annotation("org.openjdk.jmh.annotations.State")
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
    kapt("com.dslplatform:dsl-json-java8:1.9.9")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("com.dslplatform:dsl-json-java8:1.9.9")
    implementation("cc.plural:jsonij:0.5.2")
    implementation("com.alibaba:fastjson:1.2.79")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.1")
}

tasks.test {
    useJUnit()
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
