package io.logz.logsgenerator.resources

import io.logz.junit.extension.flakytest.FlakyTest
import io.logz.logsgenerator.StandaloneLogsGenerator
import mu.KLogging
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

class __camelname__ResourceITest {

    companion object: KLogging() {
        private val standaloneLogsGenerator = StandaloneLogsGenerator()

        @JvmStatic
        @BeforeAll
        fun setup() {
            standaloneLogsGenerator.start()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            standaloneLogsGenerator.stop()
        }
    }

    @FlakyTest
    fun testInitiation() {
        logger.info("Initiated ${this.javaClass.name}")
    }
}