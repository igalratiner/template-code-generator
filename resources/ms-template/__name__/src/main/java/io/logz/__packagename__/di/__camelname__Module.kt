package io.logz.__packagename__.di

import com.google.inject.AbstractModule
import io.logz.infrastructure.configuration.ConfigurationProvider
import io.logz.__packagename__.configuration.__camelname__Configuration

class __camelname__Module (
        private val configurationProvider: ConfigurationProvider<__camelname__Configuration>
): AbstractModule() {

    override fun configure() {

    }
}