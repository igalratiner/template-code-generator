package io.logz.__packagename__.di

import com.google.inject.AbstractModule
import io.logz.infrastructure.configuration.ConfigurationProvider
import io.logz.__packagename__.__camelname__EngineConfiguration

class __camelname__Module (
        private val engineConfigurationProvider: ConfigurationProvider<__camelname__EngineConfiguration>
): AbstractModule() {

    override fun configure() {

    }
}