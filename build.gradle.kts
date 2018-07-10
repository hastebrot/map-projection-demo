import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradleWrapperVersion: String by project
val kotlinVersion: String by project
val junitJupiterVersion by extra { "5.2.0" }
val myLibraryVersion by extra { "0.0.0" }

plugins {
    val kotlinVersion = "1.2.51"

    kotlin("jvm") version kotlinVersion
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib", kotlinVersion))
    compile(kotlin("stdlib-jdk7", kotlinVersion))
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
}

dependencies {
    testCompile(kotlin("test", kotlinVersion))
    testCompile(kotlin("test-junit5", kotlinVersion))
    testCompile("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks {
    withType<Wrapper> {
        gradleVersion = gradleWrapperVersion
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
