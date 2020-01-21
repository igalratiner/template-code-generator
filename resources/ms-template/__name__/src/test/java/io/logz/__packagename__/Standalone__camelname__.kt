package io.logz.__packagename__

import com.logshero.gaia.infra.engine.StandaloneEngineContainer
import java.time.Duration


class Standalone__camelname__ {
    private val container = StandaloneEngineContainer("__camelname__", "__name__.conf", ::__camelname__EngineModules, ::__camelname__EngineConfiguration)
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