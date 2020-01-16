package io.logz.logsgenerator.di

import com.google.inject.AbstractModule
import io.logz.infrastructure.configuration.ConfigurationProvider
import io.logz.logsgenerator.configuration.LogsGeneratorConfiguration

class __camelname__Module (
        private val configurationProvider: ConfigurationProvider<__camelname__Configuration>
): AbstractModule() {

    override fun configure() {

    }
}