package io.logz.__packagename__.configuration

import com.logshero.gaia.infra.engine.EngineConfiguration
import org.json.simple.JSONObject

class __camelname__Configuration(json: JSONObject)  : EngineConfiguration(json) {
    private val __methodname__Object = sectionObject.getJsonObject("__methodname__")

    val __methodname__Configuration = __camelname__DatasourceConfiguration(logsGeneratorObject)
}