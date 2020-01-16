package io.logz.logsgenerator

import com.logshero.gaia.infra.engine.StandaloneEngineContainer
import io.logz.logsgenerator.configuration.LogsGeneratorConfiguration
import java.time.Duration


class Standalone__camelname__ {
    private val container = StandaloneEngineContainer("__camelname__", "__name__.conf", ::__camelname__Modules, ::__camelname__Configuration)
    private val jsonEngineConfiguration =  container.jsonEngineConfiguration

    fun start() {
        container.start(Duration.ofSeconds(600))
    }

    fun stop() {
        if (container.isStarted) {
            container.stop()
        }
    }
}