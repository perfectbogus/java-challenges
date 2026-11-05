plugins {
    java
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed", "skipped") // shows result per test in terminal
    }

    val failedTests = mutableListOf<String>()

    addTestListener(object : TestListener {
        override fun beforeSuite(suite: TestDescriptor) {}
        override fun beforeTest(testDescriptor: TestDescriptor) {}

        override fun afterTest(desc: TestDescriptor, result: TestResult) {
            if (result.resultType == TestResult.ResultType.FAILURE) {
                val reason = result.exception?.message?.lineSequence()?.firstOrNull() ?: ""
                failedTests += "${desc.className} > ${desc.displayName}" +
                        if (reason.isNotBlank()) "\n      $reason" else ""
            }
        }

        override fun afterSuite(suite: TestDescriptor, result: TestResult) {
            if (suite.parent == null) { // root suite = end of the whole run
                println("\n========== TEST SUMMARY ==========")
                println("Total: ${result.testCount}, Passed: ${result.successfulTestCount}, " +
                        "Failed: ${result.failedTestCount}, Skipped: ${result.skippedTestCount}")
                if (failedTests.isNotEmpty()) {
                    println("\nFailed tests:")
                    failedTests.forEach { println("  ✗ $it") }
                }
                println("==================================")
            }
        }
    })
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
