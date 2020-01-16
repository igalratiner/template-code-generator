package io.logz.__packagename__

import com.logshero.gaia.infra.engine.EngineContainerStarter
import io.logz.__packagename__.configuration.__camelname__Configuration

object __camelname__Main {
    @JvmStatic
    fun main(args : Array<String>) {
            EngineContainerStarter.start("__name__.conf", { __camelname__Configuration(it) }, "__camelname__", { __camelname__Modules(it) })
    }

}