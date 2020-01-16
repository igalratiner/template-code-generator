package io.logz.__packagename__.resources

import io.logz.junit.extension.flakytest.FlakyTest
import io.logz.__packagename__.Standalone__camelname__
import mu.KLogging
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

class __camelname__ResourceITest {

    companion object: KLogging() {
        private val standalone__camelname__ = Standalone__camelname__()

        @JvmStatic
        @BeforeAll
        fun setup() {
            standalone__camelname__.start()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            standalone__camelname__.stop()
        }
    }

    @FlakyTest
    fun testInitiation() {
        logger.info("Initiated ${this.javaClass.name}")
    }
}