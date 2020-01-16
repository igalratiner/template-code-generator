package io.logz.__packagename__

import com.google.inject.Module
import com.logshero.gaia.infra.engine.EngineWithSharedDBModules
import io.logz.infrastructure.configuration.ConfigurationProvider
import io.logz.infrastructure.rest.server.di.RestServerModule
import io.logz.__packagename__.configuration.__camelname__Configuration
import io.logz.__packagename__.di.__camelname__Module

class __camelname__Modules constructor(
        private val configurationProvider: ConfigurationProvider<__camelname__Configuration>
) : EngineWithSharedDBModules(configurationProvider) {

    private val engineConfiguration: __camelname__Configuration = configurationProvider.provide()
    override fun listModules(): Array<Module> {
        return arrayOf(
                __camelname__Module(configurationProvider)
        )
    }

    override fun restServerModuleBuilder(): RestServerModule.Builder {
        return RestServerModule.builder()
                .addResourcePackage(javaClass.getPackage().name)
    }
}