package io.logz.__packagename__.configuration

import com.logshero.gaia.infra.engine.EngineWithSharedDBConfiguration
import org.json.simple.JSONObject

class __camelname__Configuration(json: JSONObject)  : EngineWithSharedDBConfiguration(json) {
    private val __methodname__Object = sectionObject.getJsonObject("__methodname__")
}