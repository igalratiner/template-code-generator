package io.logz.__packagename__

import com.logshero.gaia.infra.engine.EngineConfiguration
import io.logz.logsgenerator.configuration.__camelname__DataStoreConfiguration
import org.json.simple.JSONObject

class __camelname__EngineConfiguration(json: JSONObject)  : EngineConfiguration(json) {
    private val __methodname__Object = sectionObject.getJsonObject("__methodname__")

    val __methodname__Configuration = __camelname__DataStoreConfiguration(logsGeneratorObject)
}