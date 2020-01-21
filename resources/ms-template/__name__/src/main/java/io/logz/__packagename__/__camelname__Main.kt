package io.logz.__packagename__

import com.logshero.gaia.infra.engine.EngineContainerStarter

object __camelname__Main {
    @JvmStatic
    fun main(args : Array<String>) {
            EngineContainerStarter.start("__name__.conf", { __camelname__EngineConfiguration(it) }, "__camelname__", { __camelname__EngineModules(it) })
    }

}